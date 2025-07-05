package com.inner_medicine.domain.applicant.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseApplicantDto {
    private ResponseResumeDto responseResumeDto;
    private ResponseResumeEducationDto responseResumeEducationDto;
    private ResponseResumeExperienceDto responseResumeExperienceDto;
    private ResponseResumeSelfIntroductionDto responseResumeSelfIntroductionDto;
    private String regCode;
    private String jobCategory;
}
