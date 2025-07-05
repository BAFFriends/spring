package com.inner_medicine.domain.resume.entity;

import com.inner_medicine.domain.applicant.entity.Applicant;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "ResumeExperience")
public class ResumeSelfIntroduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;


}
