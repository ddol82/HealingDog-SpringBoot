package com.healing.healingdog.boarding.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewSummaryDTO {
    // boarding-service table
    private String reviewCount;
    private String scoreAverage;

}
