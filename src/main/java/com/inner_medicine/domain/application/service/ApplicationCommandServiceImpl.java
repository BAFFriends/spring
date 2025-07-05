package com.inner_medicine.domain.application.service;

import com.inner_medicine.domain.applicant.entity.Applicant;
import com.inner_medicine.domain.applicant.repository.ApplicantRepository;
import com.inner_medicine.domain.application.dto.CreateApplicationRequest;
import com.inner_medicine.domain.application.dto.JobPostAnswerRequest;
import com.inner_medicine.domain.application.entity.Application;
import com.inner_medicine.domain.application.repository.ApplicationRepository;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPost.repository.JobPostRepository;
import com.inner_medicine.domain.jobPostAnswer.entity.JobPostAnswer;
import com.inner_medicine.domain.jobPostAnswer.repository.JobPostAnswerRepository;
import com.inner_medicine.domain.jobPostQuestion.entity.JobPostQuestion;
import com.inner_medicine.domain.jobPostQuestion.repository.JobPostQuestionRepository;
import com.inner_medicine.presentation.payload.code.ErrorStatus;
import com.inner_medicine.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationCommandServiceImpl implements ApplicationCommandService {

    private final ApplicationRepository applicationRepository;
    private final ApplicantRepository applicantRepository;
    private final JobPostAnswerRepository jobPostAnswerRepository;
    private final JobPostQuestionRepository jobPostQuestionRepository;
    private final JobPostRepository jobPostRepository;

    @Override
    public void createApplication(CreateApplicationRequest request) {
        Applicant applicant = applicantRepository.findById(request.getApplicantId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.APPLICATION_APPLICANT_NOT_FOUND));

        JobPost jobPost = jobPostRepository.findById(request.getJobPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.APPLICATION_JOB_POST_NOT_FOUND));

        // 지원 내역 생성
        Application application = Application.builder()
                .applicant(applicant)
                .jobPost(jobPost)
                .appliedAt(LocalDateTime.now())
                .build();
        applicationRepository.save(application);

        // 답변 저장 - 리스트로 모아서 한번에 저장
        List<JobPostAnswer> answersToSave = new ArrayList<>();
        for (JobPostAnswerRequest answerReq : request.getAnswers()) {
            JobPostQuestion question = jobPostQuestionRepository.findById(answerReq.getJobPostQuestionId())
                    .orElseThrow(() -> new GeneralException(ErrorStatus.APPLICATION_QUESTION_NOT_FOUND));

            JobPostAnswer answer = JobPostAnswer.builder()
                    .applicant(applicant)
                    .jobPostQuestion(question)
                    .answer(answerReq.getAnswer())
                    .num(answerReq.getNum())
                    .build();

            answersToSave.add(answer);
        }
        jobPostAnswerRepository.saveAll(answersToSave);

    }
}
