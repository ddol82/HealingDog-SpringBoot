package com.healing.healingdog.boarding.controller;

import com.healing.healingdog.boarding.service.BoardingManageService;
import com.healing.healingdog.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/boarding-manage")
@RequiredArgsConstructor
public class BoardingManageController {

    private final BoardingManageService boardingManageService;

//  위탁돌봄 관리페이지 접속 시 모든 정보 불러옴
    @GetMapping("/")
    public ResponseEntity<ResponseDTO> selectBoarding(int providerCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "갤러리 조회 성공", boardingManageService.selectBoarding(providerCode)));
    }


//  위탁돌봄 정보 관리
    @GetMapping("info")
    public ResponseEntity<ResponseDTO> selectBoardingInfo(int providerCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "위탁돌봄정보 조회 성공", boardingManageService.selectBoardingInfo(providerCode)));
    }



}
