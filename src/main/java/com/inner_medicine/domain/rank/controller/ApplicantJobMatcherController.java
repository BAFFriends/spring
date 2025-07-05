package com.inner_medicine.domain.rank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inner_medicine.domain.applicant.dto.response.ResponseApplicantDto;
import com.inner_medicine.domain.jobPost.dto.JobPostResponseDto;
import com.inner_medicine.domain.rank.service.ApplicantJobMatcherService;
import com.inner_medicine.presentation.payload.code.ErrorStatus;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import com.inner_medicine.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/ranks")
@RequiredArgsConstructor
public class ApplicantJobMatcherController {

    private final ApplicantJobMatcherService applicantJobMatcherService;


    @PostMapping("/applicants/{applicantId}")
    public ApiResponseDto<List<ApplicantJobMatcherService.RankedJobPostDto>> rankJobPosts(@PathVariable Long applicantId) { // Map으로 받아 유연하게 파싱
        try {
            List<ApplicantJobMatcherService.RankedJobPostDto> rankedResults = applicantJobMatcherService.rankJobPostsByTextContent(
                    applicantId);

            return ApiResponseDto.onSuccess(rankedResults); // 200 OK와 랭킹 결과 반환

        } catch (IllegalArgumentException e) {
            // 입력 데이터 유효성 검사 실패 시 400 Bad Request 반환
            throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // 그 외 예상치 못한 오류 발생 시 500 Internal Server Error 반환
            throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);         }
    }
}
