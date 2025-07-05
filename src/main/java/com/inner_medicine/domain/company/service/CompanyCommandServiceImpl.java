package com.inner_medicine.domain.company.service;

import com.inner_medicine.domain.company.dto.RegisterCompanyDto;
import com.inner_medicine.domain.company.entity.Company;
import com.inner_medicine.domain.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyCommandServiceImpl implements CompanyCommandService{

    private final CompanyRepository companyRepository;

    @Override
    public Long registerCompany(RegisterCompanyDto dto) {
        return companyRepository.save(dto.from()).getId();
    }
}
