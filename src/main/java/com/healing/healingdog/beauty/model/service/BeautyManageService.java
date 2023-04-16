package com.healing.healingdog.beauty.model.service;

import com.healing.healingdog.beauty.model.dao.BeautyManageMapper;
import com.healing.healingdog.beauty.model.dto.BeautyDTO;
import com.healing.healingdog.beauty.model.dto.CommonDTO;
import com.healing.healingdog.common.file.model.dto.CertificatesDTO;
import com.healing.healingdog.login.model.dto.ProviderDTO;
import com.healing.healingdog.review.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
        String[] splitAddress = result.getAddress().split("\\^");
        result.setZoneCode(splitAddress[0]);
        result.setAddress(splitAddress[1]);
        result.setAddressDetail(splitAddress[2]);
        return result;
    }


    /**
     * 미용실 운영시간 조회
     */
    public Object selectBeautyTimes(int providerCode) {
        log.info("REQUEST SERVICE selectBeautyTimes = {}", providerCode);
        BeautyDTO result = beautyManageMapper.selectBeautyTimes(providerCode);
        log.info("result = {}", result.toString());
        return result;
    }
    /**
     * 미용실 가격 조회
     */
    public List<BeautyDTO> selectBeautyPrice(int providerCode) {
        log.info("REQUEST SERVICE selectBeautyPrice = {}", providerCode);
        List<BeautyDTO> result = beautyManageMapper.selectBeautyPrice(providerCode);
        log.info("result = {}", result.toString());
        return result;
    }

    /**
     * 미용실 신청내역 조회
     */
    public List<BeautyDTO> selectBeautyReservation(int providerCode) {
        log.info("REQUEST SERVICE selectBeautyReservation = {}", providerCode);
        List<BeautyDTO> result = beautyManageMapper.selectBeautyReservation(providerCode);
        log.info("result = {}", result.toString());
        return result;
    }
    /**
     * 미용실 신청내역 상세조회
     */
    public BeautyDTO selectBeautyReservationOne(int beautyReservationListCode) {
        log.info("REQUEST SERVICE selectBeautyReservationOne = {}", beautyReservationListCode);
        BeautyDTO result = beautyManageMapper.selectBeautyReservationOne(beautyReservationListCode);
        log.info("result = {}", result);
        return result;
    }

    /**
     * 미용실 리뷰 전체 조회
     */
    public List<ReviewDTO> selectReviewList(int providerCode) {
        log.info("REQUEST SERVICE selectReviewList ={}",providerCode);
        List<ReviewDTO> resultList = beautyManageMapper.selectReviewList(providerCode);
        log.info("result.toString() ={}", resultList);
        return resultList;
    }


    /**
     * 미용실 최신 리뷰 조회
     */
    public ReviewDTO selectLastReview(int providerCode , int num) {
        log.info("REQUEST SERVICE selectLastReview = {} & {}", providerCode, num);
        ReviewDTO result = beautyManageMapper.selectLastReview(providerCode, num);
        log.info("result.toString() ={}", result);
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
//    public int updateBeautyInfo( BeautyDTO beautyDTO) {
//        log.info("REQUEST SERVICE updateBeautyInfo = {}", beautyDTO);
//        int result = beautyManageMapper.updateBeautyInfo(beautyDTO);
//        log.info("result,toString() ={}", result +"개 수정 완료");
//        return result;
//    }
    public int updateBeautyInfo(BeautyDTO beautyDTO) {
        log.info("REQUEST SERVICE updateBeautyInfo = {}", beautyDTO);
        StringBuilder addressBuilder = new StringBuilder();
        addressBuilder.append(beautyDTO.getZoneCode())
                .append("^")
                .append(beautyDTO.getAddress())
                .append("^")
                .append(beautyDTO.getAddressDetail());
        beautyDTO.setAddress(addressBuilder.toString());
        int result = beautyManageMapper.updateBeautyInfo(beautyDTO);
        log.info("result,toString() = {} 수정 완료", beautyDTO);
        log.info("result,toString() = {} 수정 완료", result);
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
     *  미용실 신청내역 수락(수정)
     * */
    public int updateBeautyReservation(BeautyDTO beautyDTO) {
        log.info("REQUEST SERVICE updateBeautyReservation = {}",beautyDTO);
        int result = beautyManageMapper.updateBeautyReservation(beautyDTO);
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

    /**
     *  미용실 운영시간 삭제
     * */
    public int deleteBeautyTimes(int providerCode) {
        log.info("REQUEST SERVICE deleteBeautyTimes = {}", providerCode);
        int result = beautyManageMapper.deleteBeautyTimes(providerCode);
        log.info("result.toString() ={}", result +"개 삭제 완료.");
        return result;
    }


    public int deleteBeautyReservationOne(int mypetCode) {
        log.info("REQUEST SERVICE deleteBeautyReservationOne = {}", mypetCode);
        int result = beautyManageMapper.deleteBeautyReservationOne(mypetCode);
        log.info("result.toString() ={}", result +"개 삭제 완료.");
        return result;
    }


}
