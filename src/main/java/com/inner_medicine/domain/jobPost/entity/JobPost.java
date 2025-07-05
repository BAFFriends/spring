package com.inner_medicine.domain.jobPost.entity;

import com.inner_medicine.domain.auditing.entity.BaseTimeEntity;
import com.inner_medicine.domain.company.entity.Company;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobPost")
public class JobPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "position")
    private String position;

    @Column(name = "responsibilities")
    private String responsibilities;

    @Column(name = "employment_type")
    private EmploymentType employmentType;

    @Column(name = "working_hour")
    private Integer workingHour;

    @Column(name = "working_day")
    private Integer workingDay;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "recruitment_end")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "sub_category")
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobPost_disability_type_id", nullable = false)
    private JobPostDisabilityType jobPostDisabilityType;

    @Column(name = "disability_level")
    private DisabilityLevel disabilityLevel;

    @Column(name = "heavy_preferred")
    private Boolean heavyPreferred;

    @Column(name = "experience_year")
    private String experienceYear;

    public void linkCompany(Company company) {
        this.company = company;
    }
}
