package com.inner_medicine.domain.jobPost.service;

import com.inner_medicine.domain.company.entity.Company;
import com.inner_medicine.domain.company.repository.CompanyRepository;
import com.inner_medicine.domain.jobPost.dto.RequestJobPostDto;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPost.repository.JobPostRepository;
import com.inner_medicine.presentation.payload.code.ErrorStatus;
import com.inner_medicine.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JopPostCommandServiceImpl implements JobPostCommandService{

    private final JobPostRepository jobPostRepository;
    private final CompanyRepository companyRepository;


    @Override
    public Long writeJobPost(Long companyId, RequestJobPostDto requestJobPostDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.COMPANY_NOT_FOUND));
        JobPost jobPost = requestJobPostDto.from();
        jobPost.linkCompany(company);

        return jobPostRepository.save(jobPost).getId();
    }

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
