package com.inner_medicine.domain.applicant.service;

import com.inner_medicine.domain.applicant.dto.request.UpdateApplicantDto;
import com.inner_medicine.domain.applicant.entity.Applicant;
import com.inner_medicine.domain.applicant.repository.ApplicantRepository;
import com.inner_medicine.presentation.payload.code.ErrorStatus;
import com.inner_medicine.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicantCommandServiceImpl implements ApplicantCommandService{

    private final ApplicantRepository applicantRepository;
    @Override
    public Long registerApplicant(String username) {
        Applicant applicant = Applicant.builder()
                .username(username)
                .build();
        return applicantRepository.save(applicant).getId();
    }

    @Override
    public Long updateApplicant(Long applicantId, UpdateApplicantDto dto) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.APPLICANT_NOT_FOUND));
        applicant.rewriteResume(dto.getRequestResumeDto().from());
        applicant.rewriteResumeEducation(dto.getRequestResumeEducationDto().from());
        applicant.rewriteResumeExperience(dto.getRequestResumeExperienceDto().from());
        applicant.rewriteResumeSelfIntroduction(dto.getRequestResumeSelfIntroductionDto().from());
        applicant.rewriteRegCodeAndJobCategory(dto.getRegCode(), dto.getJobCategory());
        return applicantId;
    }
}
