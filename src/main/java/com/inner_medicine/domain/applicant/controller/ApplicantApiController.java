package com.inner_medicine.domain.applicant.controller;

import com.inner_medicine.domain.applicant.dto.request.UpdateApplicantDto;
import com.inner_medicine.domain.applicant.dto.response.ResponseApplicantDto;
import com.inner_medicine.domain.applicant.service.ApplicantCommandService;
import com.inner_medicine.domain.applicant.service.ApplicantQueryService;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "applicant api")
@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantApiController {

    private final ApplicantCommandService applicantCommandService;
    private final ApplicantQueryService applicantQueryService;

    @PostMapping
    public ApiResponseDto<Long> registerApplicant(@RequestParam String username) {
        return ApiResponseDto.onSuccess(applicantCommandService.registerApplicant(username));
    }

    @PatchMapping("/{applicantId}")
    public ApiResponseDto<Long> updateApplicantInformation(@PathVariable Long applicantId,
                                                           @RequestBody UpdateApplicantDto updateApplicantDto) {
        return ApiResponseDto.onSuccess(applicantCommandService
                .updateApplicant(applicantId, updateApplicantDto));
    }

    @GetMapping("/{applicantId}")
    public ApiResponseDto<ResponseApplicantDto> getSpecificApplicantInformation(@PathVariable Long applicantId) {
        return ApiResponseDto.onSuccess(applicantQueryService.getSpecificApplicantInformation(applicantId));
    }

}
