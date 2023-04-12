package com.healing.healingdog.review.controller;

import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.login.model.dto.ProviderDTO;
import com.healing.healingdog.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{service_category_code}")
    public ResponseEntity<ResponseDTO> selectReviewList(@PathVariable("service_category_code") int serviceCategoryCode) {
        log.info("REQUEST API selectReview ={}", serviceCategoryCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "리뷰조회 성공", reviewService.selectReviewList(serviceCategoryCode)) );
    }

    @GetMapping("/{service_category_code}/count")
    public ResponseEntity<ResponseDTO> selectReviewCount(@AuthenticationPrincipal ProviderDTO provider, @PathVariable("service_category_code") int serviceCategoryCode) {
        log.info("REQUEST API selectReviewCount provider={}",provider);
        log.info("REQUEST API selectReviewCount serviceCategoryCode={}",serviceCategoryCode);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄리뷰요약 조회 성공", reviewService.selectReviewCount(provider.getProviderCode(), serviceCategoryCode)));
    }

}
