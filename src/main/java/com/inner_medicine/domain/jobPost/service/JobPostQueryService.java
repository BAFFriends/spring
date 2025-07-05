package com.inner_medicine.domain.jobPost.service;

import com.inner_medicine.domain.jobPost.entity.JobPost;

import java.util.List;

public interface JobPostQueryService {
    List<JobPost> getAllJobPosts();
    JobPost getJobPostById(Long jobPostId);
    List<JobPost> searchJobPostsByTitle(String keyword);
}
