package com.inner_medicine.domain.applicant.dto.response;

import com.inner_medicine.domain.resume.entity.ResumeSelfIntroduction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseResumeSelfIntroductionDto {
    private String title;
    private String content;

    public static ResponseResumeSelfIntroductionDto of(ResumeSelfIntroduction resumeSelfIntroduction) {
        return ResponseResumeSelfIntroductionDto.builder()
                .title(resumeSelfIntroduction.getTitle())
                .content(resumeSelfIntroduction.getContent())
                .build();
    }
}
