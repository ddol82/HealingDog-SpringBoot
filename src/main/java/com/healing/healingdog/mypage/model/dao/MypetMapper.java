package com.healing.healingdog.mypage.model.dao;

import com.healing.healingdog.boarding.dto.ReviewSummaryDTO;
import com.healing.healingdog.mypage.model.dto.ChecklistDTO;
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

    /**
     * MyPet 사용자의 마이펫을 상세조회 합니다
     * @param mypetCode 사용자코드, 마이팻코드
     * @return xml 파일로 보낸다
     */
    MypetDTO selectMyPetDetailInfo(int mypetCode);

    /**
     * MyPet 사용자의 마이펫을 추가 합니다
     * @param mypetDTO,userCode 마이펫DTO, 사용자코드
     * @return xml 파일로 보낸다
     */
    int insertMyPetInfo(MypetDTO mypetDTO);

    /**
     * MyPet 사용자의 마이펫을 수정 합니다
     * @param mypetDTO,mypetCode  마이펫DTO, 사용자코드
     * @return xml 파일로 보낸다
     */
    int updateMyPetInfo(MypetDTO mypetDTO);

    /**
     * MyPet 사용자의 마이펫을 삭제 합니다
     * @param myPet  마이펫
     * @return xml 파일로 보낸다
     */
    int deleteMyPetInfo(MypetDTO myPet);

    /**
     * Checklist 마이펫의 체크리스트를 조회합니다
     * @param mypetCode 마이펫코드
     * @return xml 파일로 보낸다
     */
    ChecklistDTO selectMyPetCheckList(int mypetCode);

    /**
     * Checklist 마이펫의 체크리스트를 추가합니다
     * @param checklistDTO,mypetCode  체크리스트DTO, 마이펫코드
     * @return xml 파일로 보낸다
     */
    int insertMyPetCheckList(ChecklistDTO checklistDTO);

    /**
     * Checklist 마이펫의 체크리스트를 수정합니다
     * @param checklistDTO,mypetCode 체크리스트DTO, 마이펫코드
     * @return xml 파일로 보낸다
     */
    int updateMyPetCheckList(ChecklistDTO checklistDTO);

    /**
     * MyPet 사용자의 마이펫을 조회 합니다
     * @param mypetCode 마이팻코드
     * @return xml 파일로 보낸다
     */
    MypetDTO selectMyPetInfo(int mypetCode);
}
