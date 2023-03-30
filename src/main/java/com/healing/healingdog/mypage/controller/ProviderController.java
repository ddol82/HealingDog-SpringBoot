package com.healing.healingdog.mypage.controller;

import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.mypage.model.service.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param providerCode Provider의 번호
     * @return 받은값 providerCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/provider/{providerCode}")
    public ResponseEntity<ResponseDTO> selectMyProviderInfo(@PathVariable int providerCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "제공자 조회 성공", providerService.selectMyProviderInfo(providerCode)));
    }


}
