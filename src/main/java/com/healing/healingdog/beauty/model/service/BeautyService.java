package com.healing.healingdog.beauty.model.service;

import com.healing.healingdog.beauty.model.dao.BeautyMapper;
import com.healing.healingdog.beauty.model.dto.BeautyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeautyService {
    private final BeautyMapper beautyMapper;

    /**
     * 미용실 예약내역 등록
     * */
    public int registerBeautyReservation(BeautyDTO beautyDTO) {
        log.info("REQUEST SERVICE registerBeautyReservation = {}",beautyDTO);
        int result = beautyMapper.registerBeautyReservation(beautyDTO);
        log.info("result.toString() = {}", result + "개 등록 완료");
        return result;
    }
}
