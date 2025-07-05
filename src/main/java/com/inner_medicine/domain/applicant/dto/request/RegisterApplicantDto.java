package com.inner_medicine.domain.applicant.dto.request;

import com.inner_medicine.domain.resume.entity.Resume;
import lombok.Data;

@Data
public class RegisterApplicantDto {

    private String username;
    private String password;
    private Resume resume;
}
