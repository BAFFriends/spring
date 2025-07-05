package com.inner_medicine.domain.jobPostQuestion.entity;

import com.inner_medicine.domain.auditing.entity.BaseTimeEntity;
import com.inner_medicine.domain.company.entity.Company;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobPostQuestion")
public class JobPostQuestion extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "jobPost_id", nullable = false)
  private JobPost jobPost;

  @Column(name = "question", nullable = false)
  private String question;

  @Column(name = "is_required", nullable = false)
  private Boolean isRequired;

  @Column(name = "num", nullable = false)
  private Integer num;
}
