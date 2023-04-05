package com.healing.healingdog.boarding.service;

import com.healing.healingdog.boarding.dao.BoardingManageMapper;
import com.healing.healingdog.boarding.dto.BoardingBookingDTO;
import com.healing.healingdog.boarding.dto.BoardingServiceDTO;
import com.healing.healingdog.login.model.dto.UserDTO;
import com.healing.healingdog.mypage.model.dao.MypetMapper;
import com.healing.healingdog.mypage.model.dao.UserMapper;
import com.healing.healingdog.mypage.model.dto.MypetDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardingManageService {

    private final BoardingManageMapper boardingManageMapper;
    private final MypetMapper mypetMapper;
    private final UserMapper userMapper;

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
        log.info("result.toString() ={}", result + "개 삭제 완료.");
        return result;
    }

    public Object selectBoardingBooking(int providerCode) {
        log.info("REQUEST SERVICE selectBoardingBooking ={}",providerCode);
        List<BoardingBookingDTO> result = boardingManageMapper.selectBoardingBooking(providerCode);
        log.info("result.toString() ={}", result);
        return result;
    }

    public Object callSelectBoardingBookingMypetAPI(int userCode, int mypetCode) {
        log.info("REQUEST SERVICE callSelectBoardingBookingMypetAPI ={}","userCode="+userCode+", mypetCode="+mypetCode);
        HashMap<String, Object> result = new HashMap<>();
        MypetDTO mypet = mypetMapper.selectMyPetDetailInfo(userCode, mypetCode);
        UserDTO user = userMapper.selectMyUserInfo(userCode);
        result.put("mypet",mypet);
        result.put("user",user);
        log.info("result.toString() ={}", result);
        return result;
    }
}
