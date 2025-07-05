package com.inner_medicine.domain.jobPostAnswer.service;

import com.inner_medicine.domain.jobPostAnswer.dto.request.RequestJobPostAnswer;

public interface JobPostAnswerService {
  Long writeJobPostAnswer
      (Long applicantId, Long jobPostQuestionId, RequestJobPostAnswer dto);
}
