package com.healing.healingdog.login.dao;

import com.healing.healingdog.login.dto.ProviderDTO;
import com.healing.healingdog.login.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;


@Mapper
public interface UserMapper {

    //이메일 중복조회
    UserDTO selectByEmail(String email);

    //회원가입
    int insertUser(UserDTO user);

    //로그인
    Optional<UserDTO> findByUserEmail(String email);

    //유저정보조회
    UserDTO selectByUserEmail(String email);

    //로그인
    Optional<ProviderDTO> findByProviderEmail(String email);

}
