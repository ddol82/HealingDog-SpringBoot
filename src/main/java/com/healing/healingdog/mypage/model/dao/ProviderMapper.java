package com.healing.healingdog.mypage.model.dao;

import com.healing.healingdog.login.model.dto.ProviderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProviderMapper {

    /**
     * Provider 제공자의 정보를 조회 합니다.
     * @param providerCode Provider의 번호
     * @return xml 파일로 보낸다
     */
    ProviderDTO selectMyProviderInfo(int providerCode);

    /**
     * Provider 제공자의 정보를 수정(update) 합니다.
     * @param providerDTO 제공자 DTO
     * @return xml 파일로 보낸다
     */
    int updateMyProviderInfo(ProviderDTO providerDTO);

    /**
     * Provider 제공자의 정보를 삭제(탈퇴 ) 합니다
     * @param providerCode 삭제(탈퇴) 할 제공자의 코드
     * @return xml 파일로 보낸다
     */
    int deleteMyProviderInfo(int providerCode);
}
