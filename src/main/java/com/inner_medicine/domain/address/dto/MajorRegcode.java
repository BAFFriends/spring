package com.inner_medicine.domain.address.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MajorRegcode {
    SEOUL("11*000000", "서울특별시"),
    BUSAN("26*000000", "부산광역시"),
    DAEGU("27*000000", "대구광역시"),
    INCHEON("28*000000", "인천광역시"),
    GWANGJU("29*000000", "광주광역시"),
    DAEJEON("30*000000", "대전광역시"),
    ULSAN("31*000000", "울산광역시"),
    GYEONGGI("41*000000", "경기도"),
    CHUNGBUK("43*000000", "충청북도"),
    CHUNGNAM("44*000000", "충청남도"),
    JEONBUK("45*000000", "전라북도"),
    JEONNAM("46*000000", "전라남도"),
    GYEONGBUK("47*000000", "경상북도"),
    GYEONGNAM("48*000000", "경상남도"),
    JEJU("50*000000", "제주특별자치도"),
    GANGWON("51*000000", "강원특별자치도");

    private final String code; // 지역 코드
    private final String name; // 지역 이름
}
