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

        return result;
    }

}
