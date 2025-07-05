package com.inner_medicine.domain.applicant.service;

import com.inner_medicine.domain.applicant.dto.request.UpdateApplicantDto;

public interface ApplicantCommandService {

    Long registerApplicant(String username);

    Long updateApplicant(Long applicantId, UpdateApplicantDto dto);
}
