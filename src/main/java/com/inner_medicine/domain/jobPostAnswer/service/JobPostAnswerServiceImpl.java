package com.inner_medicine.domain.jobPostAnswer.service;

import com.inner_medicine.domain.applicant.entity.Applicant;
import com.inner_medicine.domain.applicant.repository.ApplicantRepository;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPostAnswer.dto.request.RequestJobPostAnswer;
import com.inner_medicine.domain.jobPostAnswer.entity.JobPostAnswer;
import com.inner_medicine.domain.jobPostAnswer.repository.JobPostAnswerRepository;
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
public class JobPostAnswerServiceImpl implements JobPostAnswerService {

  private final JobPostQuestionRepository jobPostQuestionRepository;
  private final ApplicantRepository applicantRepository;
  private final JobPostAnswerRepository jobPostAnswerRepository;

  @Override
  public Long writeJobPostAnswer
      (Long applicantId, Long jobPostQuestionId, RequestJobPostAnswer dto) {

    Applicant applicant = applicantRepository.findById(applicantId)
        .orElseThrow(() -> new GeneralException(ErrorStatus.APPLICANT_NOT_FOUND));

    JobPostQuestion question = jobPostQuestionRepository.findById(jobPostQuestionId)
        .orElseThrow(() -> new GeneralException(ErrorStatus.JOBPOSTQUESTION_NOT_FOUND));

    JobPostAnswer jobPostAnswer = dto.from();
    jobPostAnswer.linkAnswer(applicant, question);

    return jobPostAnswerRepository.save(jobPostAnswer).getId();
  }
}
