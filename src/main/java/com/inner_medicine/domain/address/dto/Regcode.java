package com.inner_medicine.domain.address.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자
@ToString
public class Regcode {
    private String code; // 지역 코드
    private String name; // 지역 이름
}
