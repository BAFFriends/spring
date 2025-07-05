package com.inner_medicine.domain.resume.entity;

import com.inner_medicine.domain.applicant.entity.Applicant;
import com.inner_medicine.domain.resume.entity.enums.DegreeType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ResumeEducation {

    private String schoolName;

    private String major;

    @Enumerated(EnumType.STRING)
    private DegreeType degree; // 예: HIGH_SCHOOL, BACHELOR, MASTER 등

    private LocalDateTime resumeEducationStartDate;

    private LocalDateTime resumeEducationEndDate;

    private Boolean isCurrentEducation;
}
