package com.healing.healingdog.beauty.controller;

import com.healing.healingdog.beauty.model.dto.BeautyDTO;
import com.healing.healingdog.beauty.model.dto.CommonDTO;
import com.healing.healingdog.beauty.model.service.BeautyManageService;
import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.common.file.model.dto.CertificatesDTO;
import com.healing.healingdog.login.model.dto.ProviderDTO;
import com.healing.healingdog.review.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
     */
    @GetMapping("/")
    public ResponseEntity<ResponseDTO> selectBeauty(int providerCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 조회 성공", beautyManageService.selectBeauty(providerCode)));
    }


    /**
     * 미용실관리 미용실 상세정보 조회
     */
    @GetMapping("/info")
    public ResponseEntity<ResponseDTO> selectBeautyInfo(@AuthenticationPrincipal ProviderDTO provider) {
        log.info("REQUEST API selectBeautyInfo ={}", provider);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 정보조회 성공", beautyManageService.selectBeautyInfo(provider.getProviderCode())));
    }
    /**
     * 미용실 운영시간 조회
     */
    @GetMapping("/times")
    public ResponseEntity<ResponseDTO> selectBeautyTimes(@RequestBody HashMap<String, String> input) {
        log.info("REQUEST API selectBeautyTimes ={}", input);
        int providerCode = Integer.parseInt(input.get("providerCode"));
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 운영시간조회 성공",beautyManageService.selectBeautyTimes(providerCode)));
    }

    /**
     * 미용실 신청내역 조회
     */
//    @GetMapping("/reservation")
//    public ResponseEntity<ResponseDTO> selectBeautyReservation(@RequestBody HashMap<String, String> input){
//        log.info("REQUEST API selectBeautyReservation ={}", input);
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"미용실 신청내역 조회 성공",beautyManageService.selectBeautyReservation(input)));
//    }
    @GetMapping("/reservation")
    public ResponseEntity<ResponseDTO> selectBeautyReservation(@AuthenticationPrincipal ProviderDTO provider){
        log.info("REQUEST API selectBeautyReservation ={}", provider);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"미용실 신청내역 조회 성공",beautyManageService.selectBeautyReservation(provider.getProviderCode())));
    }
    /**
     * 미용실 리뷰 전체 조회
     */
    @GetMapping("/review")
    public ResponseEntity<ResponseDTO> selectReviewList(@AuthenticationPrincipal ProviderDTO provider) {
        log.info("REQUEST API selectReview ={}", provider);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "리뷰조회 성공", beautyManageService.selectReviewList(provider.getProviderCode())) );
    }
    /**
     * 미용실 최신 리뷰 조회
     */
    @GetMapping("/last-review/{num}")
    public ResponseEntity<ResponseDTO> selectLastReview(@AuthenticationPrincipal ProviderDTO provider,@PathVariable int num) {
        log.info("REQUEST API selectReview ={}", provider);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "리뷰조회 성공", beautyManageService.selectLastReview(provider.getProviderCode(), num)) );
    }

    /**
     * 미용실관리 미용실정보 등록
     */
    @PostMapping("/info")
    public ResponseEntity<ResponseDTO> registerBeautyInfo(BeautyDTO beautyDTO) {
        log.info("REQUEST API registerBeautyInfo ={}", beautyDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 정보등록 성공", (beautyManageService.registerBeautyInfo(beautyDTO)) + "개"));
    }

    /**
     * 미용실 자격증 등록
     */
    @PostMapping("/certificates")
    public ResponseEntity<ResponseDTO> registerBeautyCertificates(CertificatesDTO certificatesDTO) {
        log.info("REQUEST API registerBeautyCertificates ={}", certificatesDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 자격증등록 성공", (beautyManageService.registerBeautyCertificates(certificatesDTO)) + "개"));
    }

    /**
     * 미용실 운영시간 등록
     */
    @PostMapping("/times")
    public ResponseEntity<ResponseDTO> registerBeautyTimes(CommonDTO commonDTO) {
        log.info("REQUEST API registerBeautyTimes ={}", commonDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 운영시간등록 성공", (beautyManageService.registerBeautyTimes(commonDTO)) + "개"));
    }


    /**
     * 미용실관리 미용실정보 수정
     */
    @PutMapping("/info")
    public ResponseEntity<ResponseDTO> updateBeautyInfo(BeautyDTO beautyDTO) {
        log.info("REQUEST API updateBeautyInfo ={}", beautyDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 정보수정 성공", (beautyManageService.updateBeautyInfo(beautyDTO)) + "개"));
    }

    /**
     * 미용실 카테고리 수정
     */
    @PutMapping("/categories")
    public ResponseEntity<ResponseDTO> updateBeautyCategories(BeautyDTO beautyDTO) {
        log.info("REQUEST API updateBeautyCategories ={}", beautyDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 카테고리 수정 성공", (beautyManageService.updateBeautyCategories(beautyDTO)) + "개"));
    }

    /**
     * 미용실 자격증 수정
     */
    @PutMapping("/certificates")
    public ResponseEntity<ResponseDTO> updateCertificates(CertificatesDTO certificatesDTO) {
        log.info("REQUEST API updateCertificates ={}", certificatesDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 자격증 수정 성공", (beautyManageService.updateCertificates(certificatesDTO)) + "개"));
    }

    /**
     * 미용실 운영시간 수정
     */
    @PutMapping("/times")
    public ResponseEntity<ResponseDTO> updateBeautyTimes(CommonDTO commonDTO) {
        log.info("REQUEST API updateBeautyTimes ={}", commonDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 운영시간 수정 성공", (beautyManageService.updateBeautyTimes(commonDTO)) + "개"));
    }

    /**
     * 미용실 신청내역 조회
     */
    @PutMapping("/reservation")
    public ResponseEntity<ResponseDTO> updateBeautyReservation(BeautyDTO beautyDTO){
        log.info("REQUEST API updateBeautyReservation ={}", beautyDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 신청 수락 성공",(beautyManageService.updateBeautyReservation(beautyDTO)) + "개"));

    }

    /**
     * 미용실관리 미용실정보 삭제
     */
    @DeleteMapping("/info")
    public ResponseEntity<ResponseDTO> deleteBeautyInfo(@RequestBody HashMap<String, String> input) {
        log.info("REQUEST API deleteBeautyInfo ={}", input);
        int providerCode = Integer.parseInt(input.get("providerCode"));
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 정보삭제 성공", (beautyManageService.deleteBeautyInfo(providerCode)) + "개"));
    }

    /**
     * @param input HashMap<String, String>타입으로 providerCode를 받아 정수형으로 변환.
     * @return HTTP 응답코드가 200 OK 이며, ResponseDTO 객체와 함께 응답 데이터를 반환. ResponseDTO 객체는 성공적인 응답을 표시하기 위한 HttpStatus.OK 상태코드, 응답 메시지, 그리고 삭제한 미용실 운영시간의 개수를 가진다.
     */
    @DeleteMapping("/times")
    public ResponseEntity<ResponseDTO> deleteBeautyTimes(@RequestBody HashMap<String, String>input){
        log.info("REQUEST API deleteBeautyTimes ={}", input);
        int providerCode = Integer.parseInt(input.get("providerCode"));
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 운영시간 삭제 성공", (beautyManageService.deleteBeautyTimes(providerCode)) + "개"));
    }
}
