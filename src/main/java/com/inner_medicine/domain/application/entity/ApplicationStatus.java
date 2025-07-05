package com.inner_medicine.domain.application.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "지원 상태")
public enum ApplicationStatus {

  @Schema(description = "지원 완료 - 대기중")
  APPLIED,

  @Schema(description = "합격")
  ACCEPTED,

  @Schema(description = "불합격")
  REJECTED
}
