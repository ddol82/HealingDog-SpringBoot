package com.healing.healingdog.review.service;

import com.healing.healingdog.review.dao.ReviewMapper;
import com.healing.healingdog.review.dto.ReviewCountDTO;
import com.healing.healingdog.review.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public List<ReviewDTO> selectReviewList(int serviceCategoryCode) {
        log.info("REQUEST SERVICE selectReview ={}",serviceCategoryCode);
        List<ReviewDTO> resultList = reviewMapper.selectReviewList(serviceCategoryCode);
        log.info("result.toString() ={}", resultList);
        return resultList;
    }


    public Object selectReviewCount(int providerCode, int serviceCategoryCode) {
        log.info("REQUEST SERVICE selectReviewSummary ={}",serviceCategoryCode);
        ReviewCountDTO result = reviewMapper.selectReviewCount(providerCode, serviceCategoryCode);
        return result;

    }
}
