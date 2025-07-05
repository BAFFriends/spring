package com.inner_medicine.domain.jobPost.controller;

import com.inner_medicine.domain.jobPost.dto.RequestJobPostDto;
import com.inner_medicine.domain.jobPost.service.JobPostCommandService;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-posts")
@RequiredArgsConstructor
public class JobPostApiController {

    private final JobPostCommandService jobPostCommandService;

    @PostMapping("/companies/{companyId}")
    public ApiResponseDto<Long> writeJobPost(@PathVariable Long companyId,
                                             @RequestBody RequestJobPostDto dto) {
        return ApiResponseDto.onSuccess(jobPostCommandService.writeJobPost(companyId, dto));
    }
}
