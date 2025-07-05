package com.inner_medicine.domain.applicant.dto;

import com.inner_medicine.domain.resume.entity.ResumeEducation;
import com.inner_medicine.domain.resume.entity.enums.DegreeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestResumeEducationDto {

    private String schoolName;
    private String major;
    private DegreeType degree;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isCurrent;

    public ResumeEducation from() {
        return ResumeEducation.builder()
                .degree(this.degree)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .isCurrent(this.isCurrent)
                .major(this.major)
                .schoolName(this.schoolName)
                .build();
    }
}
