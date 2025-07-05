package com.inner_medicine.domain.jobPost.controller;

import com.inner_medicine.domain.jobPost.dto.JobPostResponseDto;
import com.inner_medicine.domain.jobPost.dto.OneJobPostResponseDto;
import com.inner_medicine.domain.jobPost.dto.RequestJobPostDto;
import com.inner_medicine.domain.jobPost.entity.JobPost;
import com.inner_medicine.domain.jobPost.service.JobPostCommandService;
import com.inner_medicine.domain.jobPost.service.JobPostQueryService;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-posts")
@RequiredArgsConstructor
public class JobPostApiController {

    private final JobPostCommandService jobPostCommandService;
    private final JobPostQueryService jobPostQueryService;

    @Tag(name = "회사의 공고 생성 API")
    @PostMapping("/companies/{companyId}")
    public ApiResponseDto<Long> writeJobPost(@PathVariable Long companyId,
                                             @RequestBody RequestJobPostDto dto) {
        return ApiResponseDto.onSuccess(jobPostCommandService.writeJobPost(companyId, dto));
    }

    @Tag(name = "공고 D-Day 오름차순 전체 조회 API")
    @GetMapping()
    public ApiResponseDto<List<JobPostResponseDto>> readJobPosts() {
        List<JobPostResponseDto> jobPosts = jobPostQueryService.getAllJobPosts()
                .stream()
                .map(JobPostResponseDto::from)
                .toList();
        return ApiResponseDto.onSuccess(jobPosts);
    }

    @Tag(name = "공고 id값 단일 조회 API")
    @GetMapping("/{jobPostId}")
    public ApiResponseDto<OneJobPostResponseDto> readJobPost(@PathVariable Long jobPostId) {
        JobPost jobPost = jobPostQueryService.getJobPostById(jobPostId);
        return ApiResponseDto.onSuccess(OneJobPostResponseDto.from(jobPost));
    }

    @Tag(name = "공고 LIKE 검색어 조회 API")
    @GetMapping("/search")
    public ApiResponseDto<List<JobPostResponseDto>> searchJobPosts(@RequestParam("keyword") String keyword) {
        List<JobPostResponseDto> results = jobPostQueryService.searchJobPostsByTitle(keyword)
                .stream()
                .map(JobPostResponseDto::from)
                .toList();
        return ApiResponseDto.onSuccess(results);
    }

}
