package com.inner_medicine.domain.company.service;

import com.inner_medicine.domain.company.dto.RegisterCompanyDto;

public interface CompanyCommandService {

    Long registerCompany(RegisterCompanyDto dto);

}
