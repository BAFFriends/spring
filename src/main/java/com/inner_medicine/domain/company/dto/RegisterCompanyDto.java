package com.inner_medicine.domain.company.dto;

import com.inner_medicine.domain.company.entity.Company;
import com.inner_medicine.domain.company.entity.CompanyType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Data
public class RegisterCompanyDto {

    private String name;
    private String image;
    private String regcode;
    private String industry;
    private Integer employeeCount;
    private CompanyType companyType;
    private String websiteUrl;
    private String businessNum;
    private String phoneNum;

    public Company from(){
        return Company.builder()
                .address(this.regcode)
                .companyType(this.companyType)
                .image(this.image)
                .name(this.name)
                .businessNum(this.businessNum)
                .employeeCount(this.employeeCount)
                .industry(this.industry)
                .phoneNum(this.phoneNum)
                .websiteUrl(this.websiteUrl)
                .build();
    }
}
