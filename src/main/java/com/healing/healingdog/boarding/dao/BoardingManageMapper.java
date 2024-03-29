package com.healing.healingdog.boarding.dao;

import com.healing.healingdog.boarding.dto.BoardingBookingDTO;
import com.healing.healingdog.boarding.dto.BoardingServiceDTO;
import com.healing.healingdog.boarding.dto.ReviewSummaryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface BoardingManageMapper {


    BoardingServiceDTO selectBoardingInfo(int providerCode);

    int registerBoardingInfo(BoardingServiceDTO boardingServiceDTO);

    int updateBoardingInfo(BoardingServiceDTO boardingServiceDTO);

    int deleteBoardingInfo(int providerCode);

    List<BoardingBookingDTO> selectBoardingBooking(int providerCode);

    ReviewSummaryDTO selectBoardingReviewSummary(int providerCode);

    String selectBoardingIncomeByMonth(int providerCode, Timestamp selectedDate);

    String selectBoardingIncomeByYear(int providerCode, Timestamp selectedDate);
}
