package com.healing.healingdog.mypage.model.service;

import com.healing.healingdog.mypage.model.dao.MypetMapper;
import com.healing.healingdog.mypage.model.dto.MypetDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MypetService {
    private final MypetMapper mypetMapper;

    public MypetService(MypetMapper mypetMapper) {
        this.mypetMapper = mypetMapper;
    }

    /**
     *Service
     * MyPet 사용자의 마이펫을 목록 조회 합니다
     * @param userCode 사용자코드
     * @return mypetList 마이펫리스트를 반환한다
     */
    public List<MypetDTO> selectMyPetList(int userCode) {
        log.info("[MypetService] selectMyPetList Start ==============================");

        List<MypetDTO> mypetList= mypetMapper.selectMyPetList(userCode);
        log.debug("[MypetService] {}", mypetList);

        log.info("[MypetService] selectMyPetList End ==============================");
        return mypetList;
    }
}
