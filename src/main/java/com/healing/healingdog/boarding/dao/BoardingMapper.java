package com.healing.healingdog.boarding.dao;

import com.healing.healingdog.boarding.dto.BoardingBookingDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardingMapper {


    int registerBoardingBooking(BoardingBookingDTO boardingBookingDTO);

    int deleteBoardingBooking(BoardingBookingDTO boardingBookingDTOe);

    BoardingBookingDTO selectBoardingBooking(BoardingBookingDTO boardingBookingDTO);
}
