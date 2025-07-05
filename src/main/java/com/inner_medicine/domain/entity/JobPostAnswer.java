package com.inner_medicine.domain.entity;

import com.inner_medicine.domain.application.entity.Application;
import com.inner_medicine.domain.auditing.entity.BaseTimeEntity;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobPostAnswer")
public class JobPostAnswer extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "application_id", nullable = false)
  private Application application;

  @Column(name = "answer")
  private String answer;

  @Column(name = "num", nullable = false)
  private Integer num;
}
