package com.inner_medicine.domain.application.controller;


import com.inner_medicine.domain.application.dto.CreateApplicationRequest;
import com.inner_medicine.domain.application.service.ApplicationCommandService;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationApiController {

    private final ApplicationCommandService applicationService;

    @PostMapping
    public ApiResponseDto<Void> createApplication(@RequestParam Long jobPostId,
                                                  @RequestParam Long applicantId
    ) {
        CreateApplicationRequest request = new CreateApplicationRequest();
        request.setJobPostId(jobPostId);
        request.setApplicantId(applicantId);

        return ApiResponseDto.onSuccess(null);
    }
}
