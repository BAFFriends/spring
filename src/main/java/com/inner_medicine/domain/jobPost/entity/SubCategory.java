package com.inner_medicine.domain.jobPost.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "직종 소분류")
public enum SubCategory {

  경영_비지니스기획(MainCategory.기획_전략),
  웹기획(MainCategory.기획_전략),
  마케팅기획(MainCategory.기획_전략),
  PL_PM_PO(MainCategory.기획_전략),
  컨설턴트(MainCategory.기획_전략),
  CEO_COO_CTO(MainCategory.기획_전략),
  AI기획자(MainCategory.기획_전략),
  AI사업전략담당자(MainCategory.기획_전략);

  private final MainCategory mainCategory;
}
