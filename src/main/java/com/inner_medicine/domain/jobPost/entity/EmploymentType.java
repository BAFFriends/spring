package com.inner_medicine.domain.jobPost.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "고용 형태")
public enum EmploymentType {
    정규직,
    계약직,
    파견직,
    인턴,
    프리랜서,
    위촉직,
    별정직,
    아르바이트,
    기타
}
