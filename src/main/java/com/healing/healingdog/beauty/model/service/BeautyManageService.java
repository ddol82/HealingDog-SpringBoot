package com.healing.healingdog.beauty.model.service;

import com.healing.healingdog.beauty.model.dao.BeautyManageMapper;
import com.healing.healingdog.beauty.model.dto.BeautyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeautyManageService {
    private final BeautyManageMapper beautyManageMapper;

    public Object selectBeauty(int providerCode) {
        return "result";
    }

    /**
     *  미용실 정보조회
     * */
    public Object selectBeautyInfo(int providerCode){
        log.info("REQUEST SERVICE selectBeautyInfo = {}", providerCode);
        BeautyDTO result = beautyManageMapper.selectBeautyInfo(providerCode);
        log.info("result = {}", result.toString());
        return result;
    }

    /**
     *  미용실 정보 등록
     * */
    public int registerBeautyInfo(BeautyDTO beautyDTO) {
        log.info("REQUEST SERVICE registerBeautyInfo = {}",beautyDTO);
        int result = beautyManageMapper.registerBeautyInfo(beautyDTO);
        log.info("result.toString() = {}", result + "개 등록 완료");
        return result;
    }

    /**
     *  미용실 정보 수정
     * */
    public int updateBeautyInfo(BeautyDTO beautyDTO) {
        log.info("REQUEST SERVICE updateBeautyInfo = {}",beautyDTO);
        int result = beautyManageMapper.updateBeautyInfo(beautyDTO);
        log.info("result,toString() ={}", result +"개 수정 완료");
        return result;
    }

    /**
     *  미용실 정보 삭제
     * */
    public int deleteBeautyInfo(int providerCode) {
        log.info("REQUEST SERVICE deleteBeautyInfo = {}", providerCode);
        int result = beautyManageMapper.deleteBeautyInfo(providerCode);
        log.info("result.toString() ={}", result +"개 삭제 완료.");
        return result;
    }
}
