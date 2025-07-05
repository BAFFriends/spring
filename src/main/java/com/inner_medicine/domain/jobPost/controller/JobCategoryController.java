package com.inner_medicine.domain.jobPost.controller;

import com.inner_medicine.domain.jobPost.dto.JobPostResponseDto;
import com.inner_medicine.domain.jobPost.entity.MainCategory;
import com.inner_medicine.domain.jobPost.entity.SubCategory;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-posts")
@RequiredArgsConstructor
public class JobCategoryController {

  @Tag(name = "직종 대분류 카테고리 조회 API")
  @GetMapping("/main-categories")
  public ApiResponseDto<List<String>> getMainCategories() {
    List<String> mainCategories = Arrays.stream(MainCategory.values())
        .map(Enum::name)
        .toList();
    return ApiResponseDto.onSuccess(mainCategories);
  }

  @Tag(name = "직종 소분류 카테고리 조회 API")
  @GetMapping("/sub-categories")
  public ApiResponseDto<List<String>> getSubCategories() {
    List<String> subCategories = Arrays.stream(SubCategory.values())
        .map(Enum::name)
        .toList();
    return ApiResponseDto.onSuccess(subCategories);
  }
}
