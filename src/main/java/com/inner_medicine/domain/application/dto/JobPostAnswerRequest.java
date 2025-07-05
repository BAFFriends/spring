package com.inner_medicine.domain.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobPostAnswerRequest {
    @Schema(description = "공고 질문 ID", example = "1")
    private Long jobPostQuestionId;

    @Schema(description = "답변 내용", example = "답변입니다.")
    private String answer;

    @Schema(description = "질문 번호", example = "1")
    private Integer num;
}