package com.inner_medicine.domain.rank.service;

import com.inner_medicine.domain.applicant.dto.response.*;
import com.inner_medicine.domain.applicant.entity.Applicant;
import com.inner_medicine.domain.applicant.repository.ApplicantRepository;
import com.inner_medicine.domain.jobPost.dto.JobPostResponseDto;
import com.inner_medicine.domain.jobPost.entity.DisabilityLevel;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPost.repository.JobPostRepository;
import com.inner_medicine.domain.rank.dto.JobMatchResultDto;
import com.inner_medicine.domain.rank.entity.Rank;
import com.inner_medicine.domain.rank.repository.RankRepository;
import com.inner_medicine.infra.gpt.service.OpenAIService;
import com.inner_medicine.presentation.payload.code.ErrorStatus;
import com.inner_medicine.presentation.payload.exception.GeneralException;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ApplicantJobMatcherService {

    private final ApplicantRepository applicantRepository;
    private final JobPostRepository jobPostRepository;
    private final RankRepository rankRepository;
    private final OpenAIService openAIService;

    public List<RankedJobPostDto> rankJobPostsByTextContent(Long applicantId) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.APPLICANT_NOT_FOUND));

        ResponseResumeDto resumeDto = (applicant.getResume() != null) ? ResponseResumeDto.of(applicant.getResume()) : null;
        ResponseResumeEducationDto educationDto = (applicant.getEducation() != null) ? ResponseResumeEducationDto.of(applicant.getEducation()) : null;
        ResponseResumeExperienceDto experienceDto = (applicant.getExperience() != null) ? ResponseResumeExperienceDto.of(applicant.getExperience()) : null;
        ResponseResumeSelfIntroductionDto selfIntroductionDto = (applicant.getSelfIntroduction() != null) ? ResponseResumeSelfIntroductionDto.of(applicant.getSelfIntroduction()) : null;

        ResponseApplicantDto responseApplicantDto = ResponseApplicantDto.builder()
                .responseResumeDto(resumeDto)
                .responseResumeEducationDto(educationDto)
                .responseResumeExperienceDto(experienceDto)
                .responseResumeSelfIntroductionDto(selfIntroductionDto)
                .jobCategory(applicant.getJobCategory())
                .regCode(applicant.getRegCode())
                .id(applicantId)
                .build();

        List<JobPostResponseDto> jobPostResponseDtos = jobPostRepository.findAll()
                .stream()
                .map(jobPost -> JobPostResponseDto.from(jobPost))
                .toList();

        if (jobPostResponseDtos == null || jobPostResponseDtos.isEmpty()) {
            log.warn("No job posts provided for text-based ranking.");
            return List.of();
        }

        // 1. 각 공고에 대한 매칭 점수 계산
        List<RankedJobPostDto> rankedResults = jobPostResponseDtos.stream()
                .map(jobPostDto -> calculateTextBasedMatchScore(responseApplicantDto, jobPostDto))
                .sorted(Comparator.comparingDouble(RankedJobPostDto::getScore).reversed()) // 점수 내림차순 정렬
                .collect(Collectors.toList());

        // 2. 점수를 기반으로 순위 할당
        assignRanks(rankedResults);

        log.info("Text-based job post ranking completed. Found {} ranked results.", rankedResults.size());
        return rankedResults;
    }

    /**
     * 지원자와 채용 공고 DTO 간의 텍스트 기반 매칭 점수를 계산합니다.
     * 스킬 매칭 점수와 자기소개서/상세 설명 유사성 점수를 합산합니다.
     */
    private RankedJobPostDto calculateTextBasedMatchScore(
            ResponseApplicantDto applicantDto, JobPostResponseDto jobPostDto) {

        double totalScore = 0.0;

        // --- 1. 지원자 주요 텍스트 콘텐츠 구성 ---
        // OpenAI Service에 전달할 지원자의 통합 텍스트
        StringBuilder applicantMainTextBuilder = new StringBuilder();

        // 자기소개서 내용
        if (applicantDto.getResponseResumeSelfIntroductionDto() != null &&
                applicantDto.getResponseResumeSelfIntroductionDto().getContent() != null) {
            applicantMainTextBuilder.append(applicantDto.getResponseResumeSelfIntroductionDto().getContent()).append(" ");
        }

        // 경력 상세 내용 (책임/역할 부분)
        if (applicantDto.getResponseResumeExperienceDto() != null &&
                applicantDto.getResponseResumeExperienceDto().getResponsibilities() != null) {
            applicantMainTextBuilder.append(applicantDto.getResponseResumeExperienceDto().getResponsibilities()).append(" ");
        }

        // 학력 전공 (관련 키워드 추출 가능성)
        if (applicantDto.getResponseResumeEducationDto() != null &&
                applicantDto.getResponseResumeEducationDto().getMajor() != null) {
            applicantMainTextBuilder.append(applicantDto.getResponseResumeEducationDto().getMajor()).append(" ");
        }

        // 지원자의 직무 카테고리 (키워드로 활용)
        if (applicantDto.getJobCategory() != null) {
            applicantMainTextBuilder.append(applicantDto.getJobCategory()).append(" ");
        }

        String applicantCombinedText = applicantMainTextBuilder.toString().trim();


        // --- 2. 채용 공고 주요 텍스트 콘텐츠 구성 ---
        // OpenAI Service에 전달할 채용 공고의 통합 텍스트
        StringBuilder jobPostMainTextBuilder = new StringBuilder();

        // 공고 제목
        if (jobPostDto.getTitle() != null) {
            jobPostMainTextBuilder.append(jobPostDto.getTitle()).append(" ");
        }
        // 회사 이름 (직무/회사 매칭 관련)
        if (jobPostDto.getCompanyName() != null) {
            jobPostMainTextBuilder.append(jobPostDto.getCompanyName()).append(" ");
        }
        // 위치 (선호 위치 매칭 관련)
        if (jobPostDto.getLocation() != null) {
            jobPostMainTextBuilder.append(jobPostDto.getLocation()).append(" ");
        }
        // 고용 형태 (일치 여부 매칭)
        if (jobPostDto.getEmploymentType() != null) {
            jobPostMainTextBuilder.append(jobPostDto.getEmploymentType()).append(" ");
        }
        // 연봉 (키워드/범위 매칭에 활용)
        if (jobPostDto.getSalary() != null) {
            jobPostMainTextBuilder.append(jobPostDto.getSalary()).append(" ");
        }
        // dDay (마감일 관련, 텍스트 유사성에는 덜 직접적이지만 포함 가능)
        if (jobPostDto.getDDay() != null) {
            jobPostMainTextBuilder.append(jobPostDto.getDDay()).append(" ");
        }

        String jobPostCombinedText = jobPostMainTextBuilder.toString().trim();


        // --- 3. 통합 텍스트 간 의미론적 유사성 점수 (OpenAIService 활용) ---
        // 이제 지원자의 전체적인 정보와 공고의 전체적인 정보를 비교합니다.
        if (!applicantCombinedText.isEmpty() && !jobPostCombinedText.isEmpty()) {
            double overallSimilarityScore = openAIService.getTextSimilarity(
                    applicantCombinedText,
                    jobPostCombinedText
            );
            totalScore += overallSimilarityScore; // 가중치 없이 직접 합산
        }

        // 만약 JobPostResponseDto에 `requiredSkills` 필드가 다시 추가된다면,
        // 이곳에 `openAIService.getSkillMatchScore`를 사용할 수 있습니다.
        // 현재는 주어진 필드에 해당 필드가 없으므로 포함하지 않습니다.

        // 점수는 0.0 ~ 1.0 사이가 됩니다.
        return new RankedJobPostDto(jobPostDto.getId(), jobPostDto.getTitle(), totalScore, 0);
    }

    // ... (assignRanks method - remains the same) ...
    // ... (RankedJobPostDto class - remains the same) ...


    /**
     * 랭킹 결과를 담을 DTO (결과 반환용)
     */
    @Data // Getter, Setter, EqualsAndHashCode, ToString 자동 생성
    @Builder
    public static class RankedJobPostDto {
        private Long jobPostId;
        private String jobPostTitle;
        private double score; // 매칭 점수
        private long rank;   // 최종 순위

        // DTO가 immutable record가 아닌 class로 변경되어야 setRank를 사용할 수 있습니다.
        // 만약 record를 사용하고 싶다면, assignRanks 메서드에서 새로운 record 인스턴스를 생성하여 리스트를 재구성해야 합니다.
        // 이 예시에서는 Lombok의 @Data를 사용하여 Setter를 자동으로 생성하므로 문제가 없습니다.
    }

    private void assignRanks(List<RankedJobPostDto> rankedResults) {
        if (rankedResults.isEmpty()) {
            return;
        }

        long currentRank = 1;
        double previousScore = rankedResults.get(0).getScore();

        for (int i = 0; i < rankedResults.size(); i++) {
            RankedJobPostDto result = rankedResults.get(i);
            if (result.getScore() < previousScore) {
                currentRank = i + 1;
                previousScore = result.getScore();
            }
            result.setRank(currentRank); // DTO의 랭크 필드 업데이트
        }
    }

}