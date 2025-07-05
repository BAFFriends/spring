package com.inner_medicine.domain.jobPostAnswer.entity;

import com.inner_medicine.domain.applicant.entity.Applicant;
import com.inner_medicine.domain.application.entity.Application;
import com.inner_medicine.domain.auditing.entity.BaseTimeEntity;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPostQuestion.entity.JobPostQuestion;
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
  @JoinColumn(name = "applicant_id", nullable = false)
  private Applicant applicant;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "jobPostQuestion_id", nullable = false)
  private JobPostQuestion jobPostQuestion;

  @Column(name = "answer", columnDefinition = "TEXT")
  private String answer;

  @Column(name = "num", nullable = false)
  private Integer num;
}
