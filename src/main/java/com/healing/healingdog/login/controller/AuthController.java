package com.healing.healingdog.login.controller;


import com.healing.healingdog.common.ResponseDto;
import com.healing.healingdog.login.dto.UserDto;
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

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody UserDto userDto) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "회원가입 성공", authService.signup(userDto)));
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody UserDto userDto) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "로그인 성공", authService.login(userDto)));
    }

}