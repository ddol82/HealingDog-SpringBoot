package com.healing.healingdog.boarding.service;

import com.healing.healingdog.boarding.dao.BoardingManageMapper;
import com.healing.healingdog.boarding.dto.BoardingServiceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardingManageService {

    private final BoardingManageMapper boardingManageMapper;

    public Object selectBoarding(int providerCode) {

        return "result";
    }

    public Object selectBoardingInfo(int providerCode) {
        log.info("REQUEST SERVICE selectBoardingInfo ={}",providerCode);
        BoardingServiceDTO result = boardingManageMapper.selectBoardingInfo(providerCode);
        log.info("result.toString() ={}", result.toString());
        return result;
    }

    public int registerBoardingInfo(BoardingServiceDTO boardingServiceDTO) {
        log.info("REQUEST SERVICE BoardingServiceDTO ={}",boardingServiceDTO);
        int result = boardingManageMapper.registerBoardingInfo(boardingServiceDTO);
        log.info("result.toString() ={}", result + "개 등록 완료.");
        return result;
    }

    public int updateBoardingInfo(BoardingServiceDTO boardingServiceDTO) {
        log.info("REQUEST SERVICE updateBoardingInfo ={}",boardingServiceDTO);
        int result = boardingManageMapper.updateBoardingInfo(boardingServiceDTO);
        log.info("result.toString() ={}", result + "개 수정 완료.");
        return result;
    }

    public int deleteBoardingInfo(int providerCode) {
        log.info("REQUEST SERVICE selectBoardingInfo ={}",providerCode);
        int result = boardingManageMapper.deleteBoardingInfo(providerCode);
        log.info("result.toString() ={}", result + "개 수정 완료.");
        return result;
    }
}
