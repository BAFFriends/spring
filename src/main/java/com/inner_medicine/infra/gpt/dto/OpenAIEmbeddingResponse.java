package com.inner_medicine.infra.gpt.dto;

import lombok.Data;

import java.util.List;

@Data
public class OpenAIEmbeddingResponse {
    private List<EmbeddingData> data;

    @Data
    public static class EmbeddingData {
        private String object;
        private int index;
        private List<Double> embedding; // The embedding vector
    }

    private Usage usage;

    @Data
    public static class Usage {
        private int prompt_tokens;
        private int total_tokens;
    }
}
