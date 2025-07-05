package com.inner_medicine.domain.jobPostQuestion.service;

import com.inner_medicine.domain.jobPostQuestion.dto.request.RequestJobPostQuestion;

public interface JobPostQuestionService {

  Long writeJobPostQuestion(Long jobPostId, RequestJobPostQuestion dto);
}
