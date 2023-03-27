package com.healing.healingdog.login.dao;

import com.healing.healingdog.login.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    //유저정보조회
    UserDTO selectByUserEmail(String email);

}
