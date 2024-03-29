package com.healing.healingdog.mypage.controller;

import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.login.model.dto.UserDTO;
import com.healing.healingdog.mypage.model.dto.ChecklistDTO;
import com.healing.healingdog.mypage.model.dto.MypetDTO;
import com.healing.healingdog.mypage.model.service.MypetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mypage")
public class MypetController {
    private final MypetService mypetService;

    public MypetController(MypetService mypetService) {
        this.mypetService = mypetService;
    }

    /**
     *Controller
     * Mypet 사용자의 마이펫을 목록조회합니다
     * @param user 사용자의 코드
     * @return 받은값 userCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/mypet/list")
    public ResponseEntity<ResponseDTO> selectMyPetList(@AuthenticationPrincipal UserDTO user) {

        List<MypetDTO> mypetList = mypetService.selectMyPetList(user.getUserCode());

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "마이펫 목록 조회 성공", mypetList));
    }

    /**
     *Controller
     * Mypet 사용자의 마이펫을 조회 합니다
     * @param mypetCode 사용자의 코드
     * @return 받은값 mypetCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/mypet/{mypetCode}")
    public ResponseEntity<ResponseDTO> selectMyPetInfo(@PathVariable int mypetCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "마이펫 정보조회 성공", mypetService.selectMyPetInfo(mypetCode)));
    }

    /**
     *Controller
     * Mypet 사용자의 마이펫을 상세조회합니다
     * @param mypetCode 마이펫 코드
     * @return 받은값 user.getUserCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/mypet/detail/{mypetCode}")
    public ResponseEntity<ResponseDTO> selectMyPetDetailInfo(@PathVariable int mypetCode) {


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "마이펫 상세정보조회 성공", mypetService.selectMyPetDetailInfo(mypetCode)));
    }

    /**
     *Controller
     * Mypet 사용자의 마이펫을 추가합니다
     * @param user,mypetDTO 사용자 코드,마이펫DTO
     * @return 받은값 userCode,mypetDTO 을 데이터에 담아 반환한다.
     */
    @PostMapping("/mypet/insert")
    public ResponseEntity<ResponseDTO> insertMyPetInfo(@RequestBody MypetDTO mypetDTO, @AuthenticationPrincipal UserDTO user) {

        mypetDTO.setUserCode(user.getUserCode());

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "마이펫 추가 성공", mypetService.insertMyPetInfo(mypetDTO)));
    }

    /**
     *Controller
     * Mypet 사용자의 마이펫을 수정합니다
     * @param mypetDTO,mypetCode  마이펫 DTO 마이펫 코드
     * @return 받은값 mypetDTO,mypetCode 을 데이터에 담아 반환한다.
     */
    @PutMapping("/mypet/detail/{mypetCode}")
    public ResponseEntity<ResponseDTO> updateMyPetInfo(@RequestBody MypetDTO mypetDTO ,@PathVariable int mypetCode) {

        mypetDTO.setMypetCode(mypetCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "마이펫 수정 성공",  mypetService.updateMyPetInfo(mypetDTO)));
    }

    /**
     *Controller
     * Mypet 사용자의 마이펫을 삭제합니다
     * @param mypetCode,userCode 마이펫코드, 사용자코드
     * @return 받은값 mypetCode,userCode 을 데이터에 담아 반환한다.
     */
    @DeleteMapping("/mypet/detail/{mypetCode}")
    public ResponseEntity<ResponseDTO> deleteMyPetInfo(@PathVariable int mypetCode, @AuthenticationPrincipal UserDTO user) {

        MypetDTO myPet = new MypetDTO();
        myPet.setMypetCode(mypetCode);
        myPet.setUserCode(user.getUserCode());

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "마이펫 삭제 성공",  mypetService.deleteMyPetInfo(myPet)));
    }

    /**
     *Controller
     * Checklist 마이펫의 체크리스트를 조회합니다
     * @param mypetCode 마이펫의 코드
     * @return 받은값 mypetCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/mypet/checklist/{mypetCode}")
    public ResponseEntity<ResponseDTO> selectMyPetCheckList(@PathVariable int mypetCode) {



        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "마이펫 체크리스트조회 성공", mypetService.selectMyPetCheckList(mypetCode)));
    }

    /**
     *Controller
     * Checklist 마이펫의 체크리스트를 추가합니다
     * @param checklistDTO,mypetCode 체크리스트DTO, 마이펫코드
     * @return 받은값 checklistDTO 을 데이터에 담아 반환한다.
     */
    @PostMapping("/mypet/insert/{mypetCode}")
    public ResponseEntity<ResponseDTO> insertMyPetCheckList(@RequestBody ChecklistDTO checklistDTO, @PathVariable int mypetCode) {

        checklistDTO.setMypetCode(mypetCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "마이펫 체크리스트 추가 성공", mypetService.insertMyPetCheckList(checklistDTO)));
    }

    /**
     *Controller
     * Checklist 마이펫의 체크리스트를 수정 합니다
     * @param checklistDTO,mypetCode 체크리스트DTO, 마이펫코드
     * @return 받은값 checklistDTO,mypetCode 을 데이터에 담아 반환한다.
     */
    @PutMapping("/mypet/checklist/{mypetCode}")
    public ResponseEntity<ResponseDTO> updateMyPetCheckList(@RequestBody ChecklistDTO checklistDTO ,@PathVariable int mypetCode) {

        checklistDTO.setMypetCode(mypetCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "마이펫 체크리스트 수정 성공",  mypetService.updateMyPetCheckList(checklistDTO)));
    }

}
