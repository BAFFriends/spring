package com.inner_medicine.domain.jobPost.dto;

import com.inner_medicine.domain.jobPost.entity.EmploymentType;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPost.entity.MainCategory;
import com.inner_medicine.domain.jobPost.entity.SubCategory;
import jakarta.persistence.Column;
import lombok.Data;
import java.time.LocalDate;

@Data
public class RequestJobPostDto {
    private String title;
    private String description;
    private String position;
    private String responsibilities;
    private EmploymentType employmentType;
    private Integer workingHour;
    private Integer workingDay;
    private String salary;
    private String gender;
    private String age;
    private LocalDate startDate;
    private LocalDate endDate;
    private MainCategory mainCategory;
    private SubCategory subCategory;
    private Integer numberOfOpenings;
    private String experienceRequirement;
    private String educationRequirement;

    public JobPost from() {
        return JobPost.builder()
                .title(this.title)
                .description(this.description)
                .position(this.position)
                .responsibilities(this.responsibilities)
                .employmentType(this.employmentType)
                .workingHour(this.workingHour)
                .workingDay(this.workingDay)
                .salary(this.salary)
                .gender(this.gender)
                .age(this.age)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .subCategory(this.subCategory)
                .numberOfOpenings(this.numberOfOpenings)
                .experienceRequirement(this.experienceRequirement)
                .educationRequirement(this.educationRequirement)
                .build();
    }
}
