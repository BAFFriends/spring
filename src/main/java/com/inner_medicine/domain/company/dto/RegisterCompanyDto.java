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
    private String regcode;
    private Integer employeeCount;
    private CompanyType companyType;
    private String callNumber;

    public Company from(){
        return Company.builder()
                .addressCode(this.regcode)
                .companyType(this.companyType)
                .name(this.name)
                .callNumber(this.callNumber)
                .build();
    }
}
