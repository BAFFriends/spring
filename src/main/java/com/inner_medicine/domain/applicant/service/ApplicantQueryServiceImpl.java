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

        // 각 Embedded 객체가 null이 아닌 경우에만 DTO로 변환
        ResponseResumeDto resumeDto = (applicant.getResume() != null) ? ResponseResumeDto.of(applicant.getResume()) : null;
        ResponseResumeEducationDto educationDto = (applicant.getEducation() != null) ? ResponseResumeEducationDto.of(applicant.getEducation()) : null;
        ResponseResumeExperienceDto experienceDto = (applicant.getExperience() != null) ? ResponseResumeExperienceDto.of(applicant.getExperience()) : null;
        ResponseResumeSelfIntroductionDto selfIntroductionDto = (applicant.getSelfIntroduction() != null) ? ResponseResumeSelfIntroductionDto.of(applicant.getSelfIntroduction()) : null;

        return ResponseApplicantDto.builder()
                .responseResumeDto(resumeDto)
                .responseResumeEducationDto(educationDto)
                .responseResumeExperienceDto(experienceDto)
                .responseResumeSelfIntroductionDto(selfIntroductionDto)
                .jobCategory(applicant.getJobCategory())
                .regCode(applicant.getRegCode())
                .build();
    }
}
