package com.inner_medicine.domain.applicant.dto.request;

import com.inner_medicine.domain.resume.entity.Resume;
import com.inner_medicine.domain.resume.entity.enums.EmploymentStatus;
import lombok.Data;

@Data
public class RequestResumeDto {

    private EmploymentStatus employmentStatus;
    private String salaryPreference;

    public Resume from() {
        return Resume.builder()
                .salaryPreference(this.salaryPreference)
                .employmentStatus(this.employmentStatus)
                .build();
    }
}
