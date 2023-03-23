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


    public int registerBoardingBooking(BoardingBookingDTO boardingBookingDTO) {
        log.info("REQUEST SERVICE registerBoardingBooking ={}",boardingBookingDTO);
        int result = boardingMapper.registerBoardingBooking(boardingBookingDTO);
        log.info("result.toString() ={}", result + "개 등록 완료.");
        return result;
    }

    public int deleteBoardingBooking(BoardingBookingDTO boardingBookingDTOe) {
        log.info("REQUEST SERVICE deleteBoardingBooking ={}",boardingBookingDTOe);
        int result = boardingMapper.deleteBoardingBooking(boardingBookingDTOe);
        log.info("result.toString() ={}", result + "개 삭제 완료.");
        return result;
    }
}
