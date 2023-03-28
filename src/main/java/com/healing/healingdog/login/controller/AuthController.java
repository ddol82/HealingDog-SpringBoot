package com.healing.healingdog.login.controller;


import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.login.dto.ProviderDTO;
import com.healing.healingdog.login.dto.UserDTO;
import com.healing.healingdog.login.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<ResponseDTO> userSignup(@RequestBody UserDTO userDto) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "사용자 회원가입 성공", authService.userSignup(userDto)));
    }

    @PostMapping("/provider/signup")
    public ResponseEntity<ResponseDTO> providerSignup(@RequestBody ProviderDTO providerDTO) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "제공자 회원가입 성공", authService.providerSignup(providerDTO)));
    }


    @PostMapping("/user/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody UserDTO userDto) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 로그인 성공", authService.userLogin(userDto)));
    }

    @PostMapping("/provider/login")
    public ResponseEntity<ResponseDTO> providerLogin(@RequestBody ProviderDTO providerDTO) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "제공자 로그인 성공", authService.providerLogin(providerDTO)));
    }

}