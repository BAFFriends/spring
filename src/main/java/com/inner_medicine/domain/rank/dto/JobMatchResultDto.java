package com.inner_medicine.domain.rank.dto;

public record JobMatchResultDto(
        Long jobPostId,
        String jobPostTitle,
        double score,
        int rank // 이 필드는 saveRanks에서 채워짐
){}
