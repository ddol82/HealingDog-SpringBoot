package com.healing.healingdog.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.healing.healingdog.login.model.dto.RequestDTO;
import com.healing.healingdog.login.model.dto.SmsResponseDTO;
import com.healing.healingdog.login.model.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;

    @PostMapping("/user/sms")
    public ResponseEntity<SmsResponseDTO> test(@RequestBody RequestDTO request) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
        SmsResponseDTO data = smsService.sendSms(request.getRecipientPhoneNumber(), request.getContent());
        return ResponseEntity.ok().body(data);
    }
}
