package com.healing.healingdog.boarding.controller;

import com.healing.healingdog.boarding.dto.BoardingServiceDTO;
import com.healing.healingdog.boarding.service.BoardingManageService;
import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.login.model.dto.ProviderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("api/v1/boarding-management")
@RequiredArgsConstructor
public class BoardingManageController {
    private final BoardingManageService boardingManageService;

//  위탁돌봄 관리페이지 접속 시 모든 정보 불러옴
//    @GetMapping("/")
//    public ResponseEntity<ResponseDTO> selectBoarding(int providerCode) {
//        log.info("REQUEST API selectBoardingInfo ={}",providerCode);
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "갤러리 조회 성공", boardingManageService.selectBoarding(providerCode)));
//    }


//  위탁돌봄 정보 관리 CRUD
    @GetMapping("/info")
    public ResponseEntity<ResponseDTO> selectBoardingInfo(@AuthenticationPrincipal ProviderDTO provider) {
        log.info("REQUEST API selectBoardingInfo ={}",provider);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄정보 조회 성공", boardingManageService.selectBoardingInfo(provider.getProviderCode())));
    }

    @PostMapping("/info")
    public ResponseEntity<ResponseDTO> registerBoardingInfo(BoardingServiceDTO boardingServiceDTO) {
        log.info("REQUEST API registerBoardingInfo ={}",boardingServiceDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄정보 등록 성공", (boardingManageService.registerBoardingInfo(boardingServiceDTO)) + "개") );
    }

    @PutMapping("/info")
    public ResponseEntity<ResponseDTO> updateBoardingInfo(BoardingServiceDTO boardingServiceDTO) {
        log.info("REQUEST API updateBoardingInfo ={}",boardingServiceDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄정보 수정 성공", (boardingManageService.updateBoardingInfo(boardingServiceDTO)) + "개") );
    }

    @DeleteMapping("/info")
    public ResponseEntity<ResponseDTO> deleteBoardingInfo(@RequestBody HashMap<String, String> input) {
        log.info("REQUEST API deleteBoardingInfo ={}",input);
        int providerCode = Integer.parseInt(input.get("providerCode"));
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄정보 삭제 성공", (boardingManageService.deleteBoardingInfo(providerCode)) + "개"));
    }


//  위탁돌봄예약 조회
    @GetMapping("/booking")
    public ResponseEntity<ResponseDTO> selectBoardingBooking(@AuthenticationPrincipal ProviderDTO provider) {
        log.info("REQUEST API selectBoardingBooking ={}",provider);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄예약 조회 성공", boardingManageService.selectBoardingBooking(provider.getProviderCode())));
    }

    @PostMapping("/booking/mypet")
    public ResponseEntity<ResponseDTO> callSelectBoardingBookingMypetAPI(@RequestBody HashMap<String, Integer> input) {
        log.info("REQUEST API callSelectBoardingBookingMypetAPI ={}",input);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "유저&펫 조회 성공", boardingManageService.callSelectBoardingBookingMypetAPI(input.get("userCode"), input.get("mypetCode"))));
    }

//   위탁돌봄리뷰 조회
    @GetMapping("/review")
    public ResponseEntity<ResponseDTO> selectBoardingReviewSummary(@AuthenticationPrincipal ProviderDTO provider) {
        log.info("REQUEST API selectBoardingBooking ={}",provider);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄리뷰요약 조회 성공", boardingManageService.selectBoardingReviewSummary(provider.getProviderCode())));
    }

}
