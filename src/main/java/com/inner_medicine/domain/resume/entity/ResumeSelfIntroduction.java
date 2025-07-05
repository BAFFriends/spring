package com.inner_medicine.domain.resume.entity;

import com.inner_medicine.domain.applicant.entity.Applicant;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ResumeSelfIntroduction {

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;


}
