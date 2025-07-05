package com.inner_medicine.infra.gpt.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OpenAIEmbeddingRequest {
    private String model;
    private List<String> input;
    private String encoding_format; // Optional: "float"
}
