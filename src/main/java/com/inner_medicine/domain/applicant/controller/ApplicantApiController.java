package com.inner_medicine.domain.applicant.controller;

import com.inner_medicine.presentation.payload.code.ErrorStatus;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import com.inner_medicine.presentation.payload.exception.GeneralException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "applicant api")
@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantApiController {

    @GetMapping
    public Long testApi(@RequestParam Long testValue){
        return testValue;
    }

    @Operation(description = "exception test")
    @GetMapping("/exceptions")
    public ApiResponseDto<String> testException(){
        throw new GeneralException(ErrorStatus._BAD_REQUEST);
    }
}
