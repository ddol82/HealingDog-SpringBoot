package com.healing.healingdog.mypage.model.service;

import com.healing.healingdog.login.model.dto.UserDTO;
import com.healing.healingdog.mypage.model.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Slf4j
@Service
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     *Service
     *User 사용자의 정보를 조회 합니다
     * @param userCode User의 번호
     * @return user 유저정보를 반환한다
     */
    @GetMapping
    public UserDTO selectMyUserInfo(@PathVariable int userCode) {
        log.info("[UserService] selectMyUserInfo Start ==============================");

        UserDTO user = userMapper.selectMyUserInfo(userCode);
        log.info("[UserService] {}", user);
        log.info("[UserService] selectMyUserInfo End ==============================");

        return user;
    }

    /**
     *Service
     *User 사용자의 상세정보를 조회 합니다
     * @param userCode User의 번호
     * @return user 유저정보를 반환한다
     */
    public UserDTO selectMyUserDetailInfo(int userCode) {
        log.info("[UserService] selectMyUserDetailInfo Start ==============================");

        UserDTO user = userMapper.selectMyUserDetailInfo(userCode);
        log.info("[UserService] {}", user);
//        if(passwordEncoder.matches(user.getUserPassword(), user.getUserPassword())){
//
//        }
        log.info("[UserService] selectMyUserDetailInfo End ==============================");

        return user;

    }

    /**
     *Service
     *User 사용자의 정보를 수정(update) 합니다
     * @param userDTO,userCode 사용자 DTO 와 사용자코드
     * @return result 를 반환한다
     */
    public int updateMyUserDetailInfo(UserDTO userDTO, int userCode) {
        log.info("[UserService] updateMyUserDetailInfo Start ==============================");
        log.info("[UserService] {}", userDTO);

        int result = 0 ;
        userDTO.setUserCode(userCode);

        userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));

        log.info("[UserService] {}", userDTO);

        result = userMapper.updateMyUserDetailInfo(userDTO);

        log.info("[UserService] updateMyUserDetailInfo End ==============================");

        return result;
    }

    /**
     *Service
     *User 사용자의 정보를 삭제 delete(탈퇴) 합니다
     * @param userCode 삭제(탈퇴) 할 user를 userCode로
     * @return userCode 를 반환한다
     */
    public int deleteMyUserDetailInfo(int userCode) {

        return userMapper.deleteMyUserDetailInfo(userCode);
    }
}