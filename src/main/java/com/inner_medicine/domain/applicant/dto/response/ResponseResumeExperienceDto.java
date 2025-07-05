package com.inner_medicine.domain.applicant.dto.response;

import com.inner_medicine.domain.resume.entity.ResumeExperience;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseResumeExperienceDto {
    private String companyName;

    private String positionTitle;

    private String department;

    private String responsibilities;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isCurrent;

    public static ResponseResumeExperienceDto of(ResumeExperience resumeExperience) {
        return ResponseResumeExperienceDto.builder()
                .companyName(resumeExperience.getCompanyName())
                .positionTitle(resumeExperience.getPositionTitle())
                .department(resumeExperience.getDepartment())
                .responsibilities(resumeExperience.getResponsibilities())
                .startDate(resumeExperience.getResumeExperienceStartDate())
                .endDate(resumeExperience.getResumeExperienceEndDate())
                .isCurrent(resumeExperience.getIsCurrentExperience())
                .build();
    }
}
