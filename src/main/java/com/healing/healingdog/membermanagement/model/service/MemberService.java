package com.healing.healingdog.membermanagement.model.service;

import com.healing.healingdog.login.model.dto.ProviderDTO;
import com.healing.healingdog.login.model.dto.UserDTO;
import com.healing.healingdog.membermanagement.model.dao.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    /**
     *Service
     * User 사용자들을 목록 조회 합니다
     * @param
     * @return userList 사용자리스트정보를 반환한다
     */
    public List<UserDTO> selectAllUserList() {
        log.info("[MemberService] selectAllUserList Start ==============================");

        List<UserDTO> userList= memberMapper.selectAllUserList();
        log.debug("[MemberService] {}", userList);

        log.info("[MemberService] selectAllUserList End ==============================");
        return userList;
    }

    /**
     *Service
     *User 사용자의 정보를 상세조회 합니다
     * @param userCode User의 번호
     * @return user 유저정보를 반환한다
     */
    public UserDTO selectUserDetailInfo(int userCode) {
        log.info("[MemberService] selectUserDetailInfo Start ==============================");
        UserDTO user = memberMapper.selectMyUserDetailInfo(userCode);
        log.info("[MemberService] {}", user);
        log.info("[MemberService] selectUserDetailInfo End ==============================");

        return user;
    }

    /**
     *Service
     * User 사용자의 정보를 수정 합니다
     * @param userDTO,userCode 사용자DTO,사용자코드
     * @return result 반환한다
     */
    public int updateUserDetailInfo(UserDTO userDTO) {
        log.info("[MemberService] updateMyUserDetailInfo Start ==============================");
        log.info("[MemberService] {}", userDTO);
        int result = 0 ;
        log.info("[MemberService] {}", userDTO);
        result = memberMapper.updateUserDetailInfo(userDTO);
        log.info("[MemberService] updateMyUserDetailInfo End ==============================");

        return result;
    }

    /**
     *Service
     *User 사용자의 정보를 조회 합니다
     * @param userCode User의 번호
     * @return user 유저정보를 반환한다
     */
    public int deleteUserDetailInfo(int userCode) {

        return memberMapper.deleteUserDetailInfo(userCode);
    }

    /**
     *Service
     * Provider 제공자들을 목록 조회 합니다
     * @param
     * @return userList 사용자리스트정보를 반환한다
     */
    public List<ProviderDTO> selectAllProviderList() {
        log.info("[MemberService] selectAllProviderList Start ==============================");

        List<ProviderDTO> providerList= memberMapper.selectAllProviderList();
        log.debug("[MemberService] {}", providerList);

        log.info("[MemberService] selectAllProviderList End ==============================");
        return providerList;
    }

    /**
     *Service
     * Provider 제공자 정보를 상세조회 합니다
     * @param providerCode 제공자코드
     * @return provider 반환한다
     */
    public ProviderDTO selectProviderDetailInfo(int providerCode) {
        log.info("[MemberService] selectProviderDetailInfo Start ==============================");
        ProviderDTO provider = memberMapper.selectProviderDetailInfo(providerCode);
        log.info("[MemberService] {}", provider);
        log.info("[MemberService] selectProviderDetailInfo End ==============================");

        return provider;
    }

    /**
     *Service
     * Provider 제공자의 정보를 수정 합니다
     * @param providerDTO,providerCode 제공자DTO,제공자코드
     * @return result 반환한다
     */
    public int updateProviderDetailInfo(ProviderDTO providerDTO) {
        log.info("[MemberService] updateProviderDetailInfo Start ==============================");
        log.info("[MemberService] {}", providerDTO);
        int result = 0 ;
        log.info("[MemberService] {}", providerDTO);
        result = memberMapper.updateProviderDetailInfo(providerDTO);
        log.info("[MemberService] updateProviderDetailInfo End ==============================");

        return result;
    }

    /**
     *Service
     * Provider 제공자의 정보를 삭제 합니다
     * @param providerCode 제공자코드
     * @return user 유저정보를 반환한다
     */
    public int deleteProviderDetailInfo(int providerCode) {

        return memberMapper.deleteProviderDetailInfo(providerCode);
    }
}
