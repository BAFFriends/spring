package com.inner_medicine.domain.resume.entity;

import com.inner_medicine.domain.applicant.entity.Applicant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ResumeJobCategory")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ResumeJobCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    private String jobCategory; // (ex. "DEVELOPER", "DESIGNER")
}

