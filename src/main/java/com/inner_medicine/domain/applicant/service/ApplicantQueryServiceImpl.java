package com.inner_medicine.domain.applicant.service;

import com.inner_medicine.domain.applicant.dto.response.*;
import com.inner_medicine.domain.applicant.entity.Applicant;
import com.inner_medicine.domain.applicant.repository.ApplicantRepository;
import com.inner_medicine.presentation.payload.code.ErrorStatus;
import com.inner_medicine.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicantQueryServiceImpl implements ApplicantQueryService{

    private final ApplicantRepository applicantRepository;


    @Override
    public ResponseApplicantDto getSpecificApplicantInformation(Long applicantId) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.APPLICANT_NOT_FOUND));

        return ResponseApplicantDto.builder()
                .responseResumeDto(ResponseResumeDto.of(applicant.getResume()))
                .responseResumeEducationDto(ResponseResumeEducationDto.of(applicant.getEducation()))
                .responseResumeExperienceDto(ResponseResumeExperienceDto.of(applicant.getExperience()))
                .responseResumeSelfIntroductionDto(ResponseResumeSelfIntroductionDto.of(applicant.getSelfIntroduction()))
                .jobCategory(applicant.getJobCategory())
                .regCode(applicant.getRegCode())
                .build();
    }
}
