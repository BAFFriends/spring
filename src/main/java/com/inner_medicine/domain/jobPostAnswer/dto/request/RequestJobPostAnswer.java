package com.inner_medicine.domain.jobPostAnswer.dto.request;

import com.inner_medicine.domain.jobPostAnswer.entity.JobPostAnswer;
import lombok.Data;

@Data
public class RequestJobPostAnswer {

  private String answer;
  private Integer num;

  public JobPostAnswer from(){
    return JobPostAnswer.builder()
        .answer(this.answer)
        .num(this.num)
        .build();
  }
}
