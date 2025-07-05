package com.inner_medicine.domain.company.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "기업 형태")
public enum CompanyType {

    @Schema(description = "대기업")
    LARGE,

    @Schema(description = "중견기업")
    MEDIUM,

    @Schema(description = "중소기업")
    SMALL
}