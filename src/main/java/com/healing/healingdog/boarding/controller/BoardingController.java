package com.healing.healingdog.boarding.controller;

import com.healing.healingdog.boarding.dto.BoardingBookingDTO;
import com.healing.healingdog.boarding.service.BoardingService;
import com.healing.healingdog.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/boardings")
@RequiredArgsConstructor
public class BoardingController {

    private final BoardingService boardingService;

    //  위탁돌봄예약 CRUD
    @GetMapping ("/{boarding_service_code}/bookings")
    public ResponseEntity<ResponseDTO> selectBoardingBooking(@RequestBody BoardingBookingDTO boardingBookingDTO) {
        log.info("REQUEST API selectBoardingBooking ={}", boardingBookingDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄예약 삭제 성공", boardingService.selectBoardingBooking(boardingBookingDTO)) );
    }

    @PostMapping("/{boarding_service_code}/bookings")
    public ResponseEntity<ResponseDTO> registerBoardingBooking(BoardingBookingDTO boardingBookingDTO, @PathVariable("boarding_service_code") int boardingServiceCode) {
        log.info("REQUEST API registerBoardingBooking ={}",boardingBookingDTO);

        boardingBookingDTO.setBoardingServiceCode(boardingServiceCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄예약 등록 성공", (boardingService.registerBoardingBooking(boardingBookingDTO)) + "개") );
    }

    @DeleteMapping ("/{boarding_service_code}/bookings")
    public ResponseEntity<ResponseDTO> deleteBoardingBooking(@RequestBody BoardingBookingDTO boardingBookingDTO) {
        log.info("REQUEST API deleteBoardingBooking ={}", boardingBookingDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄예약 삭제 성공", (boardingService.deleteBoardingBooking(boardingBookingDTO)) + "개") );
    }



}
