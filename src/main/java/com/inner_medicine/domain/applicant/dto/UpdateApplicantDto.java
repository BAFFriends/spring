package com.inner_medicine.domain.applicant.dto;

import lombok.Data;

@Data
public class UpdateApplicantDto {
    private RequestResumeDto requestResumeDto;
    private RequestResumeEducationDto requestResumeEducationDto;
    private RequestResumeExperienceDto requestResumeExperienceDto;
    private RequestResumeSelfIntroductionDto requestResumeSelfIntroductionDto;
    private String regCode;
    private String jobCategory;
}
