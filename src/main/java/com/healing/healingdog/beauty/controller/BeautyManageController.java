package com.healing.healingdog.beauty.controller;

import com.healing.healingdog.common.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/beauty-manage")
@RequiredArgsConstructor
public class BeautyManageController {
    private final BeautyManageService beautyManageService;

    /**
     * 미용실관리 페이지 접속 시 모든정보 불러옴
     * */
    public ResponseEntity<ResponseDTO> selectBeauty(int providerCode){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"미용실 조회 성공", beautyManageService.selectBeauty(providerCode)));
    }
}
