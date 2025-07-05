package com.inner_medicine.domain.resume.entity;

import com.inner_medicine.domain.resume.entity.enums.EmploymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Resume {

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    private String salaryPreference;
}
