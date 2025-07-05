package com.inner_medicine.domain.jobPost.repository;

import com.inner_medicine.domain.jobPost.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    List<JobPost> findAllByOrderByEndDateAsc();
    List<JobPost> findByTitleContainingIgnoreCaseOrderByEndDateAsc(String keyword);
}
