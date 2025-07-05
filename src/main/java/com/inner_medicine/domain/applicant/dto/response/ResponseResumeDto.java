package com.inner_medicine.domain.applicant.dto.response;

import com.inner_medicine.domain.resume.entity.Resume;
import com.inner_medicine.domain.resume.entity.enums.EmploymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseResumeDto {

    private EmploymentStatus employmentStatus;
    private String salaryPreference;

    public static ResponseResumeDto of(Resume resume) {
        return ResponseResumeDto.builder()
                .salaryPreference(resume.getSalaryPreference())
                .employmentStatus(resume.getEmploymentStatus())
                .build();
    }
}
