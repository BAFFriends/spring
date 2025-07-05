package com.inner_medicine.domain.applicant.dto;

import com.inner_medicine.domain.resume.entity.ResumeSelfIntroduction;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RequestResumeSelfIntroductionDto {
    private String title;
    private String content;

    public ResumeSelfIntroduction from() {
        return ResumeSelfIntroduction.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
