package com.inner_medicine.domain.applicant.dto.request;

import com.inner_medicine.domain.resume.entity.ResumeExperience;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestResumeExperienceDto {
    private String companyName;

    private String positionTitle;

    private String department;

    private String responsibilities;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isCurrent;

    public ResumeExperience from() {
        return ResumeExperience.builder()
                .companyName(this.companyName)
                .positionTitle(this.positionTitle)
                .department(this.department)
                .responsibilities(this.responsibilities)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .isCurrent(this.isCurrent)
                .build();
    }
}
