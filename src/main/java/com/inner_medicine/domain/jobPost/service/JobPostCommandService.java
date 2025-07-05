package com.inner_medicine.domain.jobPost.service;

import com.inner_medicine.domain.jobPost.dto.RequestJobPostDto;

public interface JobPostCommandService {

    Long writeJobPost(Long companyId, RequestJobPostDto requestJobPostDto);
}
