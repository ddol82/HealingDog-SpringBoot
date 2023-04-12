package com.healing.healingdog.review.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewCountDTO {
    // boarding-service table
    private String reviewCount;
    private String scoreAverage;

}
