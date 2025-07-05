package com.inner_medicine.domain.jobPost.dto;

import com.inner_medicine.domain.jobPost.entity.EmploymentType;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPost.entity.MainCategory;
import com.inner_medicine.domain.jobPost.entity.SubCategory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OneJobPostResponseDto {
    private String title;
    private String description;
    private String position;
    private String responsibilities;
    private EmploymentType employmentType;
    private String location;
    private String workingHour;
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
    private String dDay;

    public static OneJobPostResponseDto from(JobPost jobPost) {
        return OneJobPostResponseDto.builder()
                .title(jobPost.getTitle())
                .description(jobPost.getDescription())
                .position(jobPost.getPosition())
                .responsibilities(jobPost.getResponsibilities())
                .employmentType(jobPost.getEmploymentType())
                .location(jobPost.getCompany().getAddressCode())
                .workingHour(jobPost.getWorkingHour() != null ? "일 " + jobPost.getWorkingHour() + "시간" : null)
                .workingDay(jobPost.getWorkingDay())
                .salary(jobPost.getSalary() != null ? jobPost.getSalary() + "만원" : "협의")
                .gender(jobPost.getGender())
                .age(jobPost.getAge())
                .startDate(jobPost.getStartDate())
                .endDate(jobPost.getEndDate())
                .mainCategory(jobPost.getSubCategory() != null ? jobPost.getSubCategory().getMainCategory() : null)
                .subCategory(jobPost.getSubCategory())
                .numberOfOpenings(jobPost.getNumberOfOpenings())
                .experienceRequirement(jobPost.getExperienceRequirement())
                .educationRequirement(jobPost.getEducationRequirement())
                .dDay(calcDday(jobPost.getEndDate()))
                .build();
    }

    private static String calcDday(LocalDate endDate) {
        if (endDate == null) return "D-∞";
        long days = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), endDate);
        return days >= 0 ? "D-" + days : "마감";
    }
}
