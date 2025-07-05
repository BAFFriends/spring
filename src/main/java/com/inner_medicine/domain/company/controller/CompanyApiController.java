package com.inner_medicine.domain.company.controller;


import com.inner_medicine.domain.company.dto.RegisterCompanyDto;
import com.inner_medicine.domain.company.service.CompanyCommandService;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyApiController {

    private final CompanyCommandService companyCommandService;

    @PostMapping
    public ApiResponseDto<Long> registerCompany(@RequestBody RegisterCompanyDto registerCompanyDto) {

        return ApiResponseDto.onSuccess(companyCommandService.registerCompany(registerCompanyDto));
    }
}
