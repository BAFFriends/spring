package com.inner_medicine.domain.resume.entity;

import com.inner_medicine.domain.applicant.entity.Applicant;
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
public class ResumeExperience {

    private String companyName;

    private String positionTitle;

    private String department;

    private String responsibilities;

    private LocalDateTime resumeExperienceStartDate;

    private LocalDateTime resumeExperienceEndDate;

    private Boolean isCurrentExperience;
}
