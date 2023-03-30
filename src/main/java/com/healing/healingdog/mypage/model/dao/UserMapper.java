package com.healing.healingdog.mypage.model.dao;

import com.healing.healingdog.login.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    /**
     * User 사용자의 정보를 조회 합니다.
     *
     * @param userCode User의 번호
     * @return xml 파일로 보낸다
     */
    UserDTO selectMyUserInfo(int userCode);

    /**
     * User 사용자의 정보를 상세조회 합니다.
     * @param userCode User의 번호
     * @return xml 파일로 보낸다
     */
    UserDTO selectMyUserDetailInfo(int userCode);

    /**
     * User 사용자의 정보를 수정(update) 합니다.
     * @param userDTO UserDTO 객체
     * @return xml 파일로 보낸다
     */
    int updateMyUserDetailInfo(UserDTO userDTO);

    /**
     * User 사용자의 정보를 삭제 delete (탈퇴) 합니다.
     * @param userCode 삭제(탈퇴) 할 user를 userCode로
     * @return xml 파일로 보낸다
     */
    int deleteMyUserDetailInfo(int userCode);
}
