package com.inner_medicine.domain.jobPost.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "직종 대분류")
public enum MainCategory {
  기획_전략,
  법무_사무_총무,
  인사_HR,
  회계_세무,
  마케팅_광고_MD,
  AI_개발_데이터,
  디자인,
  물류_무역,
  기타
}
