package com.healing.healingdog.beauty.model.service;

import com.healing.healingdog.beauty.model.dao.BeautyManageMapper;
import com.healing.healingdog.beauty.model.dto.BeautyDTO;
import com.healing.healingdog.beauty.model.dto.CommonDTO;
import com.healing.healingdog.common.file.model.dto.CertificatesDTO;
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
     * 미용실 운영시간 조회
     */
    public Object selectBeautyTimes(int providerCode) {
        log.info("REQUEST SERVICE selectBeautyTimes = {}", providerCode);
        CommonDTO result = beautyManageMapper.selectBeautyTimes(providerCode);
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
     * 미용실 자격증 등록
     * */
    public int registerBeautyCertificates(CertificatesDTO certificatesDTO) {
        log.info("REQUEST SERVICE registerBeautyCertificates = {}",certificatesDTO);
        int result =beautyManageMapper.registerBeautyCertificates(certificatesDTO);
        log.info("result.toString() = {}", result + "개 등록 완료");
        return result;
    }

    /**
     * 미용실 운영시간 등록
     */
    public int registerBeautyTimes(CommonDTO commonDTO) {
        log.info("REQUEST SERVICE registerBeautyTimes = {}",commonDTO);
        int result = beautyManageMapper.registerBeautyTimes(commonDTO);
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
     * 미용실 카테고리 수정
     */
    public int updateBeautyCategories(BeautyDTO beautyDTO) {
        log.info("REQUEST SERVICE updateBeautyCategories = {}",beautyDTO);
        int result =beautyManageMapper.updateBeautyCategories(beautyDTO);
        log.info("result,toString() = {}", result + "개 수정 완료");
        return result;
    }

    /**
     * 미용실 자격증 수정
     */
    public int updateCertificates(CertificatesDTO certificatesDTO) {
        log.info("REQUEST SERVICE updateCertificates = {}",certificatesDTO);
        int result = beautyManageMapper.updateCertificates(certificatesDTO);
        log.info("result,toString() = {}", result + "개 수정 완료");
        return result;
    }

    /**
     * 미용실 운영시간 수정
     */
    public int updateBeautyTimes(CommonDTO commonDTO) {
        log.info("REQUEST SERVICE updateBeautyTimes = {}",commonDTO);
        int result = beautyManageMapper.updateBeautyTimes(commonDTO);
        log.info("result,toString() = {}", result + "개 수정 완료");
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
