package com.healing.healingdog.boarding.dao;

import com.healing.healingdog.boarding.dto.BoardingBookingDTO;
import com.healing.healingdog.boarding.dto.BoardingServiceDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardingManageMapper {


    BoardingServiceDTO selectBoardingInfo(int providerCode);

    int registerBoardingInfo(BoardingServiceDTO boardingServiceDTO);

    int updateBoardingInfo(BoardingServiceDTO boardingServiceDTO);

    int deleteBoardingInfo(int providerCode);

    List<BoardingBookingDTO> selectBoardingBooking(int providerCode);
}
