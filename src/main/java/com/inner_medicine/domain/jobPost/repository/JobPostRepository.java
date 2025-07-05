package com.inner_medicine.domain.jobPost.repository;

import com.inner_medicine.domain.jobPost.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
}
