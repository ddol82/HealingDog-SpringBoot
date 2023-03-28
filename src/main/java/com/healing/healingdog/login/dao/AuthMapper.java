package com.healing.healingdog.login.dao;

import com.healing.healingdog.login.dto.ProviderDTO;
import com.healing.healingdog.login.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AuthMapper {

    //사용자 회원가입 이메일 중복조회
    UserDTO selectByUserSignupEmail(String email);

    //사용자 회원가입
    int insertUser(UserDTO user);

    //사용자 로그인
    Optional<UserDTO> findByUserEmail(String email);

    //제공자 로그인
    Optional<ProviderDTO> findByProviderEmail(String email);

    //제공자 회원가입 이메일 중복조회
    ProviderDTO selectByProviderSignupEmail(String email);

    //제공자 회원가입
    int insertProvider(ProviderDTO providerDTO);
}
