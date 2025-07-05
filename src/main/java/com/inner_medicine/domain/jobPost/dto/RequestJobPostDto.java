package com.inner_medicine.domain.jobPost.dto;

import com.inner_medicine.domain.company.entity.Company;
import com.inner_medicine.domain.jobPost.entity.EmploymentType;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RequestJobPostDto {
    private String title;
    private String description;
    private LocalDate start_date;
    private LocalDate end_date;
    private String responsibilities;
    private Integer salary;
    private String workTime;
    private String job_position;
    private EmploymentType employmentType;
    private String imageUrl;
    private String managerName;
    private String managerPhone;
    private String managerEmail;

    public JobPost from() {
        return JobPost.builder()
                .description(this.description)
                .position(this.job_position)
                .startDate(this.start_date)
                .endDate(this.end_date)
                .employmentType(this.employmentType)
                .responsibilities(this.responsibilities)
                .salary(this.salary)
                .title(this.title)
                .status(this.status)
                .workTime(this.workTime)
                .build();
    }
}
