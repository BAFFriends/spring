package com.inner_medicine.domain.company.repository;

import com.inner_medicine.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
