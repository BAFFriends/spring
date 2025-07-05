package com.inner_medicine.domain.jobPostQuestion.dto.request;

import com.inner_medicine.domain.jobPostQuestion.entity.JobPostQuestion;
import lombok.Data;

@Data
public class RequestJobPostQuestion {
  private String question;
  private Boolean isRequired;
  private Integer num;

  public JobPostQuestion from() {
    return JobPostQuestion.builder()
        .question(this.question)
        .isRequired(this.isRequired)
        .num(this.num)
        .build();
  }
}
