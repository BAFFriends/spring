package com.inner_medicine.domain.jobPost.service;

import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPost.repository.JobPostRepository;
import com.inner_medicine.presentation.payload.code.ErrorStatus;
import com.inner_medicine.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JobPostQueryServiceImpl implements JobPostQueryService{

    private final JobPostRepository jobPostRepository;

    @Override
    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAllByOrderByEndDateAsc();
    }

    @Override
    public JobPost getJobPostById(Long jobPostId) {
        return jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.APPLICATION_JOB_POST_NOT_FOUND));
    }
}
