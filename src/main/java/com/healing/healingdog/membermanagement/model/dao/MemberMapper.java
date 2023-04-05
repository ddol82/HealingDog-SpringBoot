package com.healing.healingdog.membermanagement.model.dao;

import com.healing.healingdog.login.model.dto.ProviderDTO;
import com.healing.healingdog.login.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    /**
     * User 사용자들을 목록조회 합니다
     * @param
     * @return xml 파일로 보낸다
     */
    List<UserDTO> selectAllUserList();

    /**
     * User 사용자의 정보를 상세조회 합니다.
     * @param userCode 사용자코드
     * @return xml 파일로 보낸다
     */
    UserDTO selectMyUserDetailInfo(int userCode);

    /**
     * User 사용자의 정보를 수정 합니다.
     * @param userDTO,userCode 사용자DTO, 사용자코드
     * @return xml 파일로 보낸다
     */
    int updateUserDetailInfo(UserDTO userDTO);

    /**
     * User 사용자의 정보를 삭제 delete 합니다.
     * @param userCode 사용자코드
     * @return xml 파일로 보낸다
     */
    int deleteUserDetailInfo(int userCode);

    /**
     * Provider 제공자들을 목록조회 합니다
     * @param
     * @return xml 파일로 보낸다
     */
    List<ProviderDTO> selectAllProviderList();

    /**
     * Provider 제공자의 정보를 상세조회 합니다.
     * @param providerCode 제공자코드
     * @return xml 파일로 보낸다
     */
    ProviderDTO selectProviderDetailInfo(int providerCode);

    /**
     * Provider 제공자의 정보를 수정 합니다.
     * @param providerDTO,providerCode 제공자DTO, 제공자코드
     * @return xml 파일로 보낸다
     */
    int updateProviderDetailInfo(ProviderDTO providerDTO);

    /**
     * Provider 제공자의 정보를 삭제 합니다.
     * @param providerCode 제공자코드
     * @return xml 파일로 보낸다
     */
    int deleteProviderDetailInfo(int providerCode);
}
