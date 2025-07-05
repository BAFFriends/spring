package com.inner_medicine.domain.application.entity;

import com.inner_medicine.domain.applicant.entity.Applicant;
import com.inner_medicine.domain.auditing.entity.BaseTimeEntity;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "application")
public class Application extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "jobPost_id", nullable = false)
  private JobPost jobPost;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "applicant_id", nullable = false)
  private Applicant applicant;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "resume_id", nullable = false)
  private Resume resume;

  @Column(name = "applied_at", nullable = false)
  private LocalDateTime appliedAt;
}
