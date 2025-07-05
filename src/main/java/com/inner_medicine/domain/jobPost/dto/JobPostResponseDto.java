package com.inner_medicine.domain.jobPost.dto;

import com.inner_medicine.domain.jobPost.entity.JobPost;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JobPostResponseDto {
    private Long id;
    private String companyName;
    private String title;
    private String salary;
    private String location;
    private String workingHour;
    private String employmentType;
    private String position;
    private String responsibilities;
    private String dDay;

    public static JobPostResponseDto from(JobPost jobPost) {
        return JobPostResponseDto.builder()
                .id(jobPost.getId())
                .companyName(jobPost.getCompany().getName())
                .title(jobPost.getTitle())
                .salary(jobPost.getSalary() != null ? jobPost.getSalary() + "만원" : "협의")
                .location(jobPost.getCompany().getAddressCode())
                .workingHour(jobPost.getWorkingHour() != null ? "일 " + jobPost.getWorkingHour() + "시간" : null)
                .employmentType(jobPost.getEmploymentType().name())
                .position(jobPost.getPosition())
                .responsibilities(jobPost.getResponsibilities())
                .dDay(calcDday(jobPost.getEndDate()))
                .build();
    }

    private static String calcDday(LocalDate endDate) {
        if (endDate == null) return "D-∞";
        long days = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), endDate);
        return days >= 0 ? "D-" + days : "마감";
    }
}
