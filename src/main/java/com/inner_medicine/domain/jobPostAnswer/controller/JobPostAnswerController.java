package com.inner_medicine.domain.jobPostAnswer.controller;

import com.inner_medicine.domain.jobPostAnswer.dto.request.RequestJobPostAnswer;
import com.inner_medicine.domain.jobPostAnswer.service.JobPostAnswerService;
import com.inner_medicine.domain.jobPostQuestion.dto.request.RequestJobPostQuestion;
import com.inner_medicine.domain.jobPostQuestion.service.JobPostQuestionService;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-post-answers")
@RequiredArgsConstructor
public class JobPostAnswerController {

  private final JobPostQuestionService jobPostQuestionService;
  private final JobPostAnswerService jobPostAnswerService;

  @Tag(name = "공고 답변 생성 API")
  @PostMapping("/{applicantId}/{jobPostQuestionId}")
  public ApiResponseDto<Long> writeJobPostQuestion(
      @Parameter(description = "답변을 작성하는 지원자 ID")
      @PathVariable Long applicantId,
      @Parameter(description = "채용 공고 질문 ID")
      @PathVariable Long jobPostQuestionId,
      @RequestBody RequestJobPostAnswer dto) {

    return ApiResponseDto.onSuccess(jobPostAnswerService.writeJobPostAnswer(applicantId, jobPostQuestionId, dto));
  }
}
