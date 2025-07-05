package com.inner_medicine.domain.resume.entity;

import com.inner_medicine.domain.applicant.entity.Applicant;
import com.inner_medicine.domain.resume.entity.enums.DegreeType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "ResumeEducation")
public class ResumeEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    private String schoolName;

    private String major;

    @Enumerated(EnumType.STRING)
    private DegreeType degree; // 예: HIGH_SCHOOL, BACHELOR, MASTER 등

    private String startDate;

    private String endDate;

    private Boolean isCurrent;
}
