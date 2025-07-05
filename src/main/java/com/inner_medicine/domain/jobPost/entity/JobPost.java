package com.inner_medicine.domain.jobPost.entity;

import com.inner_medicine.domain.auditing.entity.BaseTimeEntity;
import com.inner_medicine.domain.company.entity.Company;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Getter
@Builder
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

    @Column(name = "start", nullable = false)
    private LocalDateTime start_date;

    @Column(name = "end", nullable = false)
    private LocalDateTime end_date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private JobPostStatus status;

    @Column(name = "salary")
    private String salary;

    @Column(name = "work_time")
    private String workTime;

    @Column(name = "position")
    private String job_position;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "manager_phone")
    private String managerPhone;

    @Column(name = "manager_email")
    private String managerEmail;
}
