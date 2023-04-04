package com.healing.healingdog.mypage.model.service;

import com.healing.healingdog.mypage.model.dao.MypetMapper;
import com.healing.healingdog.mypage.model.dto.ChecklistDTO;
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

    /**
     *Service
     * MyPet 사용자의 마이펫을 상세 조회 합니다
     * @param userCode,mypetCode 사용자코드, 마이펫코드
     * @return myPet 마이펫리스트를 반환한다
     */
    public MypetDTO selectMyPetDetailInfo(int userCode, int mypetCode) {
        log.info("[MypetService] selectMyPetDetailInfo Start ==============================");

        MypetDTO myPet = mypetMapper.selectMyPetDetailInfo(userCode,mypetCode);
        log.info("[MypetService] {}", myPet);
        log.info("[MypetService] selectMyPetDetailInfo End ==============================");

        return myPet;
    }

    /**
     *Service
     * MyPet 사용자의 마이펫을 추가 합니다
     * @param mypetDTO,userCode 마이펫DTO,사용자코드
     * @return result 를 반환한다
     */
    public int insertMyPetInfo(MypetDTO mypetDTO, int userCode) {
        log.info("[MypetService] insertMyPetInfo Start ==============================");
        log.info("[MypetService] {}", mypetDTO);
        log.info("[MypetService] {}", userCode);
        int result = 0 ;
        result = mypetMapper.insertMyPetInfo(mypetDTO,userCode);
        log.info("[MypetService] insertMyPetInfo End ==============================");

        return result;
    }

    /**
     *Service
     * MyPet 사용자의 마이펫을 수정 합니다
     * @param mypetDTO,mypetCode 마이펫DTO, 마이펫코드
     * @return mypetList 마이펫리스트를 반환한다
     */
    public int updateMyPetInfo(MypetDTO mypetDTO, int mypetCode) {
        log.info("[MypetService] updateMyPetInfo Start ==============================");
        log.info("[MypetService] {}", mypetDTO);
        log.info("[MypetService] {}", mypetCode);
        int result = 0 ;
        result = mypetMapper.updateMyPetInfo(mypetDTO,mypetCode);
        log.info("[MypetService] updateMyPetInfo End ==============================");

        return result;
    }

    /**
     *Service
     * MyPet 사용자의 마이펫을 삭제 합니다
     * @param mypetCode,userCode 마이펫코드, 사용자코드
     * @return mypetList 마이펫리스트를 반환한다
     */
    public int deleteMyPetInfo(int mypetCode, int userCode) {

        return mypetMapper.deleteMyPetInfo(mypetCode,userCode);
    }

    /**
     *Service
     * Checklist 마이펫의 체크리스트를 조회합니다
     * @param mypetCode 마이펫코드
     * @return mypetList 마이펫리스트를 반환한다
     */
    public ChecklistDTO selectMyPetCheckList(int mypetCode) {
        log.info("[MypetService] selectMyPetCheckList Start ==============================");

        ChecklistDTO checkList = mypetMapper.selectMyPetCheckList(mypetCode);
        log.info("[MypetService] {}", checkList);
        log.info("[MypetService] selectMyPetCheckList End ==============================");

        return checkList;
    }

    /**
     *Service
     * Checklist 마이펫의 체크리스트를 추가합니다
     * @param checklistDTO,mypetCode  체크리스트DTO, 마이펫코드
     * @return  result 를 반환한다
     */
    public int insertMyPetCheckList(ChecklistDTO checklistDTO, int mypetCode) {
        log.info("[MypetService] insertMyPetCheckList Start ==============================");
        log.info("[MypetService] {}", checklistDTO);
        log.info("[MypetService] {}", mypetCode);
        int result = 0 ;
        result = mypetMapper.insertMyPetCheckList(checklistDTO,mypetCode);
        log.info("[MypetService] insertMyPetCheckList End ==============================");

        return result;
    }

    /**
     *Service
     * Checklist 마이펫의 체크리스트를 수정합니다
     * @param checklistDTO,mypetCode 체크리스트DTO, 마이펫코드
     * @return result 를 반환한다
     */
    public int updateMyPetCheckList(ChecklistDTO checklistDTO, int mypetCode) {
        log.info("[MypetService] updateMyPetCheckList Start ==============================");
        log.info("[MypetService] {}", checklistDTO);
        log.info("[MypetService] {}", mypetCode);
        int result = 0 ;
        result = mypetMapper.updateMyPetCheckList(checklistDTO,mypetCode);
        log.info("[MypetService] updateMyPetCheckList End ==============================");

        return result;
    }
}
