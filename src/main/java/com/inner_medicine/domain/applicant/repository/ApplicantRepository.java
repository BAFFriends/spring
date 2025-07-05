package com.inner_medicine.domain.applicant.repository;

import com.inner_medicine.domain.applicant.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
