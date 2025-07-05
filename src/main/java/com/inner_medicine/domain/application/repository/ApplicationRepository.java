package com.inner_medicine.domain.application.repository;

import com.inner_medicine.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
