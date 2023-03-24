package com.healing.healingdog.review.controller;

import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄예약 삭제 성공", reviewService.selectReviewList(serviceCategoryCode)) );
    }


}
