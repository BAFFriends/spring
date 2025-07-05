package com.inner_medicine.domain.applicant.service;

import com.inner_medicine.domain.applicant.dto.response.ResponseApplicantDto;

public interface ApplicantQueryService {

    ResponseApplicantDto getSpecificApplicantInformation(Long applicantId);
}
