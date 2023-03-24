package com.healing.healingdog.login.dao;

import com.healing.healingdog.login.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;


@Mapper
public interface UserMapper {

    //이메일 중복조회
    UserDto selectByEmail(String email);

    //회원가입
    int insertUser(UserDto user);

    //로그인
    Optional<UserDto> findByUserEmail(String email);

    //유저정보조회
    UserDto selectByUserEmail(String email);
}
