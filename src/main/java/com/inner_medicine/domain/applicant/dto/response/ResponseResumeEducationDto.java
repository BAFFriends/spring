package com.inner_medicine.domain.applicant.dto.response;

import com.inner_medicine.domain.resume.entity.ResumeEducation;
import com.inner_medicine.domain.resume.entity.enums.DegreeType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseResumeEducationDto {

    private String schoolName;
    private String major;
    private DegreeType degree;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isCurrent;

    public static ResponseResumeEducationDto of(ResumeEducation resumeEducation) {
        return ResponseResumeEducationDto.builder()
                .degree(resumeEducation.getDegree())
                .startDate(resumeEducation.getStartDate())
                .endDate(resumeEducation.getEndDate())
                .isCurrent(resumeEducation.getIsCurrent())
                .major(resumeEducation.getMajor())
                .schoolName(resumeEducation.getSchoolName())
                .build();
    }
}
