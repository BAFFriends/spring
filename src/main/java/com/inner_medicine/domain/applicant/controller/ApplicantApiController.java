package com.inner_medicine.domain.applicant.controller;

import com.inner_medicine.domain.applicant.dto.request.UpdateApplicantDto;
import com.inner_medicine.domain.applicant.dto.response.ResponseApplicantDto;
import com.inner_medicine.domain.applicant.service.ApplicantCommandService;
import com.inner_medicine.domain.applicant.service.ApplicantQueryService;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "이력서 생성 API")
    @PostMapping
    public ApiResponseDto<Long> registerApplicant(@RequestParam String username) {
        return ApiResponseDto.onSuccess(applicantCommandService.registerApplicant(username));
    }

    @Operation(summary = "이력서 수정 API")
    @PatchMapping("/{applicantId}")
    public ApiResponseDto<Long> updateApplicantInformation(@PathVariable Long applicantId,
                                                           @RequestBody UpdateApplicantDto updateApplicantDto) {
        return ApiResponseDto.onSuccess(applicantCommandService
                .updateApplicant(applicantId, updateApplicantDto));
    }

    @Operation(summary = "이력서 조회 API")
    @GetMapping("/{applicantId}")
    public ApiResponseDto<ResponseApplicantDto> getSpecificApplicantInformation(@PathVariable Long applicantId) {
        return ApiResponseDto.onSuccess(applicantQueryService.getSpecificApplicantInformation(applicantId));
    }

}
