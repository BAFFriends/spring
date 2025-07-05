package com.inner_medicine.domain.jobPost.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "장애 요구정도")
public enum DisabilityLevel {

  HEAVY,
  LIGHT,
  ANY
}
