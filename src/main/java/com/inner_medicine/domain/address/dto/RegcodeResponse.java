package com.inner_medicine.domain.address.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자
@ToString
public class RegcodeResponse {
    private List<Regcode> regcodes; // 지역 코드 리스트
}

