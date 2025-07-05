package com.inner_medicine.domain.applicant.entity;

import com.inner_medicine.domain.resume.entity.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(unique = true)
    private String username;

    @Embedded
    private Resume resume;

    @Embedded
    private ResumeEducation education;

    @Embedded
    private ResumeExperience experience;

    @Embedded
    private ResumeSelfIntroduction selfIntroduction;

    private String regCode;

    private String jobCategory;

    public void rewriteResume(Resume resume) {
        this.resume = resume;
    }

    public void rewriteResumeEducation(ResumeEducation resumeEducation) {
        this.education = resumeEducation;
    }

    public void rewriteResumeExperience(ResumeExperience resumeExperience) {
        this.experience = resumeExperience;
    }

    public void rewriteResumeSelfIntroduction(ResumeSelfIntroduction resumeSelfIntroduction) {
        this.selfIntroduction = resumeSelfIntroduction;
    }

    public void rewriteRegCodeAndJobCategory(String regCode, String jobCategory) {
        this.regCode = regCode;
        this.jobCategory = jobCategory;
    }
}
