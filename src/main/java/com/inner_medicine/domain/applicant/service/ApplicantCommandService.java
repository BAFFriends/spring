package com.inner_medicine.domain.applicant.service;

public interface ApplicantCommandService {

    Long registerApplicant(String username);

    Long updateApplicant(Long applicantId, UpdateApplicantDto dto);
}
