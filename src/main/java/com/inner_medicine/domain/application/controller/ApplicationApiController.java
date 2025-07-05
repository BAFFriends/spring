package com.inner_medicine.domain.application.controller;


import com.inner_medicine.domain.application.dto.CreateApplicationRequest;
import com.inner_medicine.domain.application.dto.JobPostAnswerRequest;
import com.inner_medicine.domain.application.service.ApplicationCommandService;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationApiController {

    private final ApplicationCommandService applicationService;

    @PostMapping("/{jobPostId}/{applicantId}")
    public ApiResponseDto<Void> createApplication(@PathVariable Long jobPostId,
                                                  @PathVariable Long applicantId,
                                                  @RequestBody List<JobPostAnswerRequest> answerRequestList
                                                  ) {
        CreateApplicationRequest request = new CreateApplicationRequest();
        request.setJobPostId(jobPostId);
        request.setApplicantId(applicantId);
        request.setAnswers(answerRequestList);

        applicationService.createApplication(request);

        return ApiResponseDto.onSuccess(null);
    }
}
