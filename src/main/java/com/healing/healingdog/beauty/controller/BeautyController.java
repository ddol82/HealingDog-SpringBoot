package com.healing.healingdog.beauty.controller;

import com.healing.healingdog.beauty.model.dto.BeautyDTO;
import com.healing.healingdog.beauty.model.service.BeautyService;
import com.healing.healingdog.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/beauties")
@RequiredArgsConstructor
public class BeautyController {
    private final BeautyService BeautyService;

    /**
     * 미용실 예약내역 등록
     * @param beautyDTO
     * @return
     */
    @PostMapping("/reservation")
    public ResponseEntity<ResponseDTO> registerBeautyReservation(BeautyDTO beautyDTO){
        log.info("REQUEST API registerBeautyReservation ={}", beautyDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 예약등록 성공",(BeautyService.registerBeautyReservation(beautyDTO)) + "개"));
    }

}
