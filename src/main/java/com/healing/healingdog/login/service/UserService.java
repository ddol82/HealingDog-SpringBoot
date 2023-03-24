package com.healing.healingdog.login.service;

import com.healing.healingdog.login.dao.UserMapper;
import com.healing.healingdog.login.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Slf4j
@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping
    public UserDTO selectMyInfo(@PathVariable String email) {
        log.info("[MemberService] getMyInfo Start ==============================");

        UserDTO user = userMapper.selectByUserEmail(email);
        log.info("[MemberService] {}", user);
        log.info("[MemberService] getMyInfo End ==============================");

        return user;
    }
}