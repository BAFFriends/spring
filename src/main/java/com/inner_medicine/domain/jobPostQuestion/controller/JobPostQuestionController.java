package com.inner_medicine.domain.jobPostQuestion.controller;

import com.inner_medicine.domain.jobPostQuestion.dto.request.RequestJobPostQuestion;
import com.inner_medicine.domain.jobPostQuestion.entity.JobPostQuestion;
import com.inner_medicine.domain.jobPostQuestion.repository.JobPostQuestionRepository;
import com.inner_medicine.domain.jobPostQuestion.service.JobPostQuestionService;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-post-questions")
@RequiredArgsConstructor
public class JobPostQuestionController {

  private final JobPostQuestionService jobPostQuestionService;

  @Tag(name = "공고 질문 생성 API")
  @PostMapping("/job-posts/{jobPostId}")
  public ApiResponseDto<Long> writeJobPostQuestion(@PathVariable Long jobPostId,
      @RequestBody RequestJobPostQuestion dto) {

    return ApiResponseDto.onSuccess(jobPostQuestionService.writeJobPostQuestion(jobPostId, dto));
  }
}
