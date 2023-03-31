package com.healing.healingdog.boarding.service;

import com.healing.healingdog.boarding.dao.BoardingMapper;
import com.healing.healingdog.boarding.dto.BoardingBookingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardingService {

    private final BoardingMapper boardingMapper;


    public Object selectBoardingBooking(BoardingBookingDTO boardingBookingDTO) {
        log.info("REQUEST SERVICE selectBoardingBooking ={}",boardingBookingDTO);
        BoardingBookingDTO result = boardingMapper.selectBoardingBooking(boardingBookingDTO);
        log.info("result.toString() ={}", result);
        return result;
    }

    public int registerBoardingBooking(BoardingBookingDTO boardingBookingDTO) {
        log.info("REQUEST SERVICE registerBoardingBooking ={}",boardingBookingDTO);
        int result = boardingMapper.registerBoardingBooking(boardingBookingDTO);
        log.info("result.toString() ={}", result + "개 등록 완료.");
        return result;
    }

    public int deleteBoardingBooking(BoardingBookingDTO boardingBookingDTO) {
        log.info("REQUEST SERVICE deleteBoardingBooking ={}",boardingBookingDTO);
        int result = boardingMapper.deleteBoardingBooking(boardingBookingDTO);
        log.info("result.toString() ={}", result + "개 삭제 완료.");
        return result;
    }


}
