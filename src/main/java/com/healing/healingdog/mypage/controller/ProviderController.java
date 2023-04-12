package com.healing.healingdog.mypage.controller;

import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.login.model.dto.ProviderDTO;
import com.healing.healingdog.mypage.model.service.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mypage")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    /**
     *Controller
     *Provider 제공자의 정보를 조회 합니다
     * @param provider 제공자코드
     * @return 받은값 providerCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/provider")
    public ResponseEntity<ResponseDTO> selectMyProviderInfo(@AuthenticationPrincipal ProviderDTO provider) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "제공자 조회 성공", providerService.selectMyProviderInfo(provider.getProviderCode())));
    }

    /**
     *Controller
     *Provider 제공자의 정보를 수정(update) 합니다
     * @param providerDTO,provider  제공자 DTO 와 제공자코드
     * @return 받은값 providerDTO,providerCode 을 데이터에 담아 반환한다.
     */
    @PutMapping("/provider")
    public ResponseEntity<ResponseDTO> updateMyProviderInfo(@RequestBody ProviderDTO providerDTO , @AuthenticationPrincipal ProviderDTO provider) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "제공자 정보 수정 성공",  providerService.updateMyProviderInfo(providerDTO,provider.getProviderCode())));
    }


    /**
     *Controller
     *Provider 제공자의 정보를 삭제(탈퇴 ) 합니다
     * @param provider 제공자코드
     * @return 받은값 providerCode 을 데이터에 담아 반환한다.
     */
    @DeleteMapping("/provider")
    public ResponseEntity<ResponseDTO> deleteMyProviderInfo(@AuthenticationPrincipal ProviderDTO provider) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "제공자 탈퇴 성공",  providerService.deleteMyProviderInfo(provider.getProviderCode())));
    }


}
