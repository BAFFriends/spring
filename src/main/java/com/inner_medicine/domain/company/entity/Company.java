package com.inner_medicine.domain.company.entity;

import com.inner_medicine.domain.auditing.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "image")
  private String image;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "industry")
  private String industry;

  @Column(name = "employee_count")
  private Integer employeeCount;

  @Column(name = "company_type", nullable = false)
  private CompanyType companyType;

  @Column(name = "website_url", nullable = false)
  private String websiteUrl;

  @Column(name = "business_num", nullable = false)
  private String businessNum;

  @Column(name = "phone_num", nullable = false)
  private String phoneNum;

}
