package com.inner_medicine.domain.applicant.service;

import com.inner_medicine.domain.applicant.entity.Applicant;
import com.inner_medicine.domain.applicant.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicantCommandServiceImpl implements ApplicantCommandService{

    private final ApplicantRepository applicantRepository;
    @Override
    public Long registerApplicant(String username) {
        Applicant applicant = Applicant.builder()
                .username(username)
                .build();
        return applicantRepository.save(applicant).getId();
    }
}
