package com.inner_medicine.domain.jobPostQuestion.repository;

import com.inner_medicine.domain.jobPostQuestion.entity.JobPostQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostQuestionRepository extends JpaRepository<JobPostQuestion,Long> {
}
