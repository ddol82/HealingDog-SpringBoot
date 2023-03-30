package com.healing.healingdog.mypage.model.dao;

import com.healing.healingdog.mypage.model.dto.MypetDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MypetMapper {

    /**
     * MyPet 사용자의 마이펫을 목록조회 합니다
     * @param userCode 사용자코드
     * @return xml 파일로 보낸다
     */
    List<MypetDTO> selectMyPetList(int userCode);
}
