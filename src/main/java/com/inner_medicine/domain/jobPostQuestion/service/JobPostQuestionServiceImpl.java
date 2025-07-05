package com.inner_medicine.domain.jobPostQuestion.service;

import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPost.repository.JobPostRepository;
import com.inner_medicine.domain.jobPostQuestion.dto.request.RequestJobPostQuestion;
import com.inner_medicine.domain.jobPostQuestion.entity.JobPostQuestion;
import com.inner_medicine.domain.jobPostQuestion.repository.JobPostQuestionRepository;
import com.inner_medicine.presentation.payload.code.ErrorStatus;
import com.inner_medicine.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JobPostQuestionServiceImpl implements JobPostQuestionService {

  private final JobPostRepository jobPostRepository;
  private final JobPostQuestionRepository jobPostQuestionRepository;

  @Override
  public Long writeJobPostQuestion(Long jobPostId, RequestJobPostQuestion dto) {
     JobPost post = jobPostRepository.findById(jobPostId)
         .orElseThrow(() -> new GeneralException(ErrorStatus.APPLICATION_JOB_POST_NOT_FOUND));
     JobPostQuestion jobPostQuestion = dto.from();
     jobPostQuestion.linkJobPost(post);

     return jobPostQuestionRepository.save(jobPostQuestion).getId();
  }
}
