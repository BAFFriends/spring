package com.inner_medicine.domain.jobPost.dto;

import com.inner_medicine.domain.company.entity.Company;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPost.entity.JobPostStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestJobPostDto {
    private String title;
    private String description;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private JobPostStatus status;
    private String salary;
    private String workTime;
    private String job_position;
    private String employmentType;
    private String imageUrl;
    private String managerName;
    private String managerPhone;
    private String managerEmail;

    public JobPost from() {
        return JobPost.builder()
                .description(this.description)
                .job_position(this.job_position)
                .start_date(this.start_date)
                .end_date(this.end_date)
                .employmentType(this.employmentType)
                .imageUrl(this.imageUrl)
                .managerEmail(this.managerEmail)
                .managerName(this.managerName)
                .managerPhone(this.managerPhone)
                .salary(this.salary)
                .title(this.title)
                .status(this.status)
                .workTime(this.workTime)
                .build();
    }
}
