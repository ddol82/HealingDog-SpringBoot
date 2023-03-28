package com.healing.healingdog.beauty.controller;

import com.healing.healingdog.beauty.model.dto.BeautyDTO;
import com.healing.healingdog.beauty.model.dto.CommonDTO;
import com.healing.healingdog.beauty.model.service.BeautyManageService;
import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.common.file.model.dto.CertificatesDTO;
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
     */
    @GetMapping("/")
    public ResponseEntity<ResponseDTO> selectBeauty(int providerCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 조회 성공", beautyManageService.selectBeauty(providerCode)));
    }

    /**
     * 미용실관리 미용실 상세정보 조회
     */
    @GetMapping("/info")
    public ResponseEntity<ResponseDTO> selectBeautyInfo(@RequestBody HashMap<String, String> input) {
        log.info("REQUEST API selectBeautyInfo ={}", input);
        int providerCode = Integer.parseInt(input.get("providerCode"));
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 정보조회 성공", beautyManageService.selectBeautyInfo(providerCode)));
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
     * 미용실관리 미용실정보 삭제
     */
    @DeleteMapping("/info")
    public ResponseEntity<ResponseDTO> deleteBeautyInfo(@RequestBody HashMap<String, String> input) {
        log.info("REQUEST API deleteBeautyInfo ={}", input);
        int providerCode = Integer.parseInt(input.get("providerCode"));
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "미용실 정보삭제 성공", (beautyManageService.deleteBeautyInfo(providerCode)) + "개"));
    }
}
