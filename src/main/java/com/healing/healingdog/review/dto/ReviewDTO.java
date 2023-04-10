package com.healing.healingdog.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private int reviewsCode;
    private int serviceCategoryCode;
    private int providerCode;
    private int userCode;
    private Timestamp registDate;
    private String content;
    private int score;
    private String nickname;
}
