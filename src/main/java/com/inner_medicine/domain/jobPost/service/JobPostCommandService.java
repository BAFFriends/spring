package com.inner_medicine.domain.jobPost.service;

import com.inner_medicine.domain.jobPost.dto.RequestJobPostDto;
import com.inner_medicine.domain.jobPost.entity.JobPost;

import java.util.List;

public interface JobPostCommandService {

    Long writeJobPost(Long companyId, RequestJobPostDto requestJobPostDto);
    List<JobPost> getAllJobPosts();
}
