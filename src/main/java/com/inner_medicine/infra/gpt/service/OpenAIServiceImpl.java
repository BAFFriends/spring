package com.inner_medicine.infra.gpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inner_medicine.infra.gpt.dto.OpenAIChatRequest;
import com.inner_medicine.infra.gpt.dto.OpenAIChatResponse;
import com.inner_medicine.infra.gpt.dto.OpenAIEmbeddingRequest;
import com.inner_medicine.infra.gpt.dto.OpenAIEmbeddingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAIServiceImpl implements OpenAIService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper; // JSON 파싱을 위한 ObjectMapper

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.chat.model:gpt-3.5-turbo}") // 기본값 설정
    private String chatModel;

    @Value("${openai.embedding.model:text-embedding-ada-002}") // 기본값 설정
    private String embeddingModel;

    private static final String CHAT_COMPLETIONS_URL = "https://api.openai.com/v1/chat/completions";
    private static final String EMBEDDINGS_URL = "https://api.openai.com/v1/embeddings";

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * 텍스트에서 핵심 기술 스택을 추출합니다.
     * Chat Completions API를 사용하여 모델에게 스킬 목록을 JSON 형식으로 반환하도록 지시합니다.
     */
    @Override
    public List<String> extractSkillsFromText(String text) {
        String prompt = String.format(
                "다음 텍스트에서 주요 기술 스택을 식별하여 JSON 배열 형태로 나열하세요. 각 스킬은 쉼표로 구분된 문자열이어야 합니다. 텍스트에 기술 스택이 없으면 빈 배열을 반환합니다. 예를 들어, [\"Java\", \"Spring Boot\", \"MySQL\"].\n\n텍스트: %s",
                text
        );

        OpenAIChatRequest request = OpenAIChatRequest.builder()
                .model(chatModel)
                .messages(List.of(
                        OpenAIChatRequest.Message.builder().role("system").content("당신은 기술 스택 추출 전문가입니다. 주어진 텍스트에서 기술 스택을 정확하게 식별하여 JSON 배열로 반환하세요.").build(),
                        OpenAIChatRequest.Message.builder().role("user").content(prompt).build()
                ))
                .build();

        HttpEntity<OpenAIChatRequest> entity = new HttpEntity<>(request, createHeaders());

        try {
            ResponseEntity<OpenAIChatResponse> response = restTemplate.postForEntity(CHAT_COMPLETIONS_URL, entity, OpenAIChatResponse.class);

            if (response.getBody() != null && !response.getBody().getChoices().isEmpty()) {
                String content = response.getBody().getChoices().get(0).getMessage().getContent();
                // 모델이 JSON 형식으로 응답했는지 확인하고 파싱
                try {
                    // 모델이 간혹 ```json ... ``` 형태로 반환할 수 있으므로 앞뒤를 제거합니다.
                    if (content.startsWith("```json") && content.endsWith("```")) {
                        content = content.substring(7, content.length() - 3).trim();
                    }
                    JsonNode rootNode = objectMapper.readTree(content);
                    if (rootNode.isArray()) {
                        return Arrays.stream(objectMapper.convertValue(rootNode, String[].class))
                                .collect(Collectors.toList());
                    }
                } catch (JsonProcessingException e) {
                    log.error("Failed to parse JSON response for skill extraction: {}", content, e);
                    // JSON 파싱 실패 시, 쉼표로 구분된 문자열로 간주하고 파싱 시도 (fallback)
                    return Arrays.stream(content.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                }
            }
        } catch (Exception e) {
            log.error("Error calling OpenAI API for skill extraction: {}", e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 지원자 스킬과 채용 공고 설명 간의 매칭 점수를 반환합니다.
     * 여기서는 두 텍스트의 핵심 스킬을 추출한 다음, 공통 스킬의 개수를 기반으로 점수를 계산합니다.
     * 더 고급 매칭을 위해선 의미론적 유사도(getTextSimilarity)를 활용할 수 있습니다.
     */
    @Override
    public double getSkillMatchScore(String applicantSkills, String jobDescription) {
        // 1. 지원자 스킬 추출
        List<String> applicantSkillList = extractSkillsFromText(applicantSkills);
        // 2. 공고 요구 스킬 추출
        List<String> jobRequiredSkillList = extractSkillsFromText(jobDescription);

        if (applicantSkillList.isEmpty() || jobRequiredSkillList.isEmpty()) {
            return 0.0; // 한쪽이라도 스킬이 없으면 매칭 점수 0
        }

        // 3. 공통 스킬 계산 (대소문자 무시)
        long commonSkillsCount = applicantSkillList.stream()
                .map(String::toLowerCase)
                .filter(applicantSkill -> jobRequiredSkillList.stream()
                        .map(String::toLowerCase)
                        .anyMatch(jobSkill -> jobSkill.contains(applicantSkill) || applicantSkill.contains(jobSkill))) // 부분 일치도 고려
                .count();

        // 4. 점수 계산: (공통 스킬 수 / 채용 공고 요구 스킬 수)
        // 공고가 요구하는 스킬 중 얼마나 많은 스킬을 지원자가 가지고 있는지
        return (double) commonSkillsCount / jobRequiredSkillList.size();
    }


    /**
     * 두 텍스트의 의미론적 유사성 점수를 반환합니다.
     * OpenAI Embeddings API를 사용하여 각 텍스트의 벡터 임베딩을 얻은 후,
     * 두 벡터 간의 코사인 유사도를 계산합니다.
     */
    @Override
    public double getTextSimilarity(String text1, String text2) {
        try {
            // 1. 두 텍스트의 임베딩 벡터 생성 요청
            OpenAIEmbeddingRequest request = OpenAIEmbeddingRequest.builder()
                    .model(embeddingModel)
                    .input(List.of(text1, text2))
                    .build();

            HttpEntity<OpenAIEmbeddingRequest> entity = new HttpEntity<>(request, createHeaders());

            ResponseEntity<OpenAIEmbeddingResponse> response = restTemplate.postForEntity(EMBEDDINGS_URL, entity, OpenAIEmbeddingResponse.class);

            if (response.getBody() != null && response.getBody().getData() != null && response.getBody().getData().size() == 2) {
                List<Double> embedding1 = response.getBody().getData().get(0).getEmbedding();
                List<Double> embedding2 = response.getBody().getData().get(1).getEmbedding();

                // 2. 코사인 유사도 계산
                return cosineSimilarity(embedding1, embedding2);
            }
        } catch (Exception e) {
            log.error("Error calling OpenAI Embeddings API for text similarity: {}", e.getMessage(), e);
        }
        return 0.0; // 에러 발생 시 0 반환
    }

    /**
     * 두 벡터 간의 코사인 유사도를 계산합니다.
     * 코사인 유사도 = (A · B) / (||A|| · ||B||)
     * (A · B)는 내적, ||A||는 벡터의 크기(L2 Norm)
     */
    private double cosineSimilarity(List<Double> vector1, List<Double> vector2) {
        if (vector1.size() != vector2.size() || vector1.isEmpty()) {
            return 0.0;
        }

        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < vector1.size(); i++) {
            dotProduct += vector1.get(i) * vector2.get(i);
            norm1 += Math.pow(vector1.get(i), 2);
            norm2 += Math.pow(vector2.get(i), 2);
        }

        if (norm1 == 0 || norm2 == 0) {
            return 0.0; // 0으로 나누는 것 방지
        }

        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
}
