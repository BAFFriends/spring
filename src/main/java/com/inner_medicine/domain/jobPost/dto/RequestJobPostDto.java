package com.inner_medicine.domain.jobPost.dto;

import com.inner_medicine.domain.jobPost.entity.EmploymentType;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPost.entity.MainCategory;
import com.inner_medicine.domain.jobPost.entity.SubCategory;
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
    private Integer salary;
    private String gender;
    private String age;
    private LocalDate startDate;
    private LocalDate endDate;
    private MainCategory mainCategory;
    private SubCategory subCategory;

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
                .mainCategory(this.mainCategory)
                .subCategory(this.subCategory)
                .build();
    }
}
