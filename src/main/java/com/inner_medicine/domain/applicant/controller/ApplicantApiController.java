package com.inner_medicine.domain.applicant.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantApiController {

    @GetMapping
    public Long testApi(@RequestParam Long testValue){
        return testValue;
    }
}
