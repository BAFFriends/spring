package com.inner_medicine.domain.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateApplicationRequest {
    @Schema(description = "지원할 공고 ID", example = "1")
    private Long jobPostId;

    @Schema(description = "지원자 ID", example = "1")
    private Long applicantId;

    private List<JobPostAnswerRequest> answers;
}





