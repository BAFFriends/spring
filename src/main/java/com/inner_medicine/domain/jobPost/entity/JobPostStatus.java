package com.inner_medicine.domain.jobPost.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공고 상태")
public enum JobPostStatus {

    @Schema(description = "모집 예정")
    UPCOMING,

    @Schema(description = "모집")
    OPEN,

    @Schema(description = "모집 마감")
    CLOSED
} 