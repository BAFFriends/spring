package com.inner_medicine.domain.address.controller;

import com.inner_medicine.domain.address.dto.MajorRegcode;
import com.inner_medicine.domain.address.dto.RegcodeResponse;
import com.inner_medicine.domain.address.service.AddressQueryService;
import com.inner_medicine.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressApiController {

    private final AddressQueryService addressQueryService;

    @GetMapping
    public ApiResponseDto<RegcodeResponse> getAllAddressesInKorea(
            @RequestParam MajorRegcode majorRegcode) {
        return ApiResponseDto.onSuccess(addressQueryService.getAddresses(majorRegcode.getCode()));
    }
}
