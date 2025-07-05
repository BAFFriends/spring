package com.inner_medicine.domain.applicant.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "disability_type")
public class DisabilityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disabilityTypeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DisabilityEnum disability; // enum 타입 필드

    @OneToMany(mappedBy = "disabilityType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalDisability> personalDisabilities = new ArrayList<>();

}

enum DisabilityEnum {
    PHYSICAL,                // 지체 장애
    BRAIN_LESION,            // 뇌병변 장애
    VISUAL,                  // 시각 장애
    HEARING,                 // 청각 장애
    SPEECH_LANGUAGE,         // 언어 장애
    INTELLECTUAL,            // 지적 장애
    AUTISM_SPECTRUM,         // 자폐성 장애
    MENTAL,                  // 정신 장애
    KIDNEY,                  // 신장 장애
    HEART,                   // 심장 장애
    RESPIRATORY,             // 호흡기 장애
    LIVER,                   // 간 장애
    FACIAL,                  // 안면 장애
    STOMA,                   // 장루·요루 장애
    EPILEPSY,                // 간질 장애
    DEVELOPMENTAL,           // 발달 장애
    INTERNAL_ORGAN           // 내부 장기 장애
}
