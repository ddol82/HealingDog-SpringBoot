package com.healing.healingdog.beauty.controller;

import com.healing.healingdog.beauty.model.dto.BeautyDTO;
import com.healing.healingdog.beauty.model.service.BeautyManageService;
import com.healing.healingdog.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/v1/beauty-manage")
@RequiredArgsConstructor
public class BeautyManageController {
    private final BeautyManageService beautyManageService;

    /**
     * 미용실관리 페이지 접속 시 모든정보 불러옴
     * */
    @GetMapping("/")
    public ResponseEntity<ResponseDTO> selectBeauty(int providerCode){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"미용실 조회 성공", beautyManageService.selectBeauty(providerCode)));
    }

    /**
     * 미용실관리 미용실 상세정보 조회
     * */
    @GetMapping("/info")
    public ResponseEntity<ResponseDTO> selectBeautyInfo(@RequestBody HashMap<String,String> input){
        log.info("REQUEST API selectBeautyInfo ={}", input);
        int providerCode = Integer.parseInt(input.get("providerCode"));
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 정보조회 성공", beautyManageService.selectBeautyInfo(providerCode)));
    }

    /**
     * 미용실관리 미용실정보 등록
     * */
    @PostMapping("/info")
    public ResponseEntity<ResponseDTO> registerBeautyInfo(BeautyDTO beautyDTO){
        log.info("REQUEST API registBeautyInfo ={}",beautyDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"미용실 정보등록 성공", (beautyManageService.registerBeautyInfo(beautyDTO)) + "개"));
    }

    /**
     * 미용실관리 미용실정보 수정
     * */
    public ResponseEntity<ResponseDTO> updateBeautyInfo(BeautyDTO beautyDTO){
        log.info("REQUEST API updateBeautyInfo ={}", beautyDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 정보수정 성공", (beautyManageService.updateBeautyInfo(beautyDTO)) + "개"));
    }

    /**
     * 미용실관리 미용실정보 삭제
     * */
    public ResponseEntity<ResponseDTO> deleteBeautyInfo(@RequestBody HashMap<String, String> input){
        log.info("REQUEST API deleteBeautyInfo ={}",input);
        int providerCode = Integer.parseInt(input.get("providerCode"));
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"미용실 정보삭제 성공",(beautyManageService.deleteBeautyInfo(providerCode)) + "개"));
    }
}
