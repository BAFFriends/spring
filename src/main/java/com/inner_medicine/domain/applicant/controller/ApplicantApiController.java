package com.inner_medicine.domain.applicant.controller;

import com.inner_medicine.domain.applicant.service.ApplicantCommandService;
import com.inner_medicine.presentation.payload.code.ErrorStatus;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import com.inner_medicine.presentation.payload.exception.GeneralException;
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

    @PostMapping
    public ApiResponseDto<Long> registerApplicant(@RequestParam String username) {
        return ApiResponseDto.onSuccess(applicantCommandService.registerApplicant(username));
    }

    @PatchMapping("/applicants/{applicantId}")
    public ApiResponseDto<Long> updateApplicantInformation(@RequestBody UpdateApplicantDto updateApplicantDto) {

    }

}
