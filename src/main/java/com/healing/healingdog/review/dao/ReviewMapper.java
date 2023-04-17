package com.healing.healingdog.review.dao;

import com.healing.healingdog.review.dto.ReviewCountDTO;
import com.healing.healingdog.review.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewDTO> selectReviewList(int serviceCategoryCode);

    ReviewCountDTO selectReviewCount(int providerCode, int serviceCategoryCode);
}
