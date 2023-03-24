package com.healing.healingdog.login.controller;


import com.healing.healingdog.common.ResponseDto;
import com.healing.healingdog.login.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<ResponseDto> selectMyMemberInfo(@PathVariable String email) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", userService.selectMyInfo(email)));
    }



}
