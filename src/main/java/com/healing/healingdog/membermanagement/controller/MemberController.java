package com.healing.healingdog.membermanagement.controller;

import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.login.model.dto.ProviderDTO;
import com.healing.healingdog.login.model.dto.UserDTO;
import com.healing.healingdog.membermanagement.model.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     *Controller
     * User 사용자들을 목록조회합니다.
     * @param
     * @return 받은값 userList 을 데이터에 담아 반환한다.
     */
    @GetMapping("/user/list")
    public ResponseEntity<ResponseDTO> selectAllUserList() {

        List<UserDTO> userList = memberService.selectAllUserList();

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자들 목록 조회 성공", userList));
    }

    /**
     *Controller
     * User 사용자의 정보를 상세조회 합니다
     * @param userCode User의 번호
     * @return 받은값 userCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/user/detail/{userCode}")
    public ResponseEntity<ResponseDTO> selectUserDetailInfo(@PathVariable int userCode) {


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 상세정보 조회 성공", memberService.selectUserDetailInfo(userCode)));
    }

    /**
     *Controller
     * User 사용자의 정보를 수정 합니다
     * @param userDTO,userCode  userDTO  userCode
     * @return 받은값 userDTO,userCode 을 데이터에 담아 반환한다.
     */
    @PutMapping("/user/detail/{userCode}")
    public ResponseEntity<ResponseDTO> updateUserDetailInfo(@RequestBody UserDTO userDTO , @PathVariable int userCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 정보 수정 성공",  memberService.updateUserDetailInfo(userDTO,userCode)));
    }

    /**
     *Controller
     * User 사용자의 정보를 삭제 합니다
     * @param userCode 사용자코드
     * @return 받은값 userCode 을 데이터에 담아 반환한다.
     */
    @DeleteMapping("/user/detail/{userCode}")
    public ResponseEntity<ResponseDTO> deleteUserDetailInfo(@PathVariable int userCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 삭제 성공",  memberService.deleteUserDetailInfo(userCode)));
    }

    /**
     *Controller
     * Provider 제공자들을 목록조회합니다.
     * @param
     * @return 받은값 providerList 을 데이터에 담아 반환한다.
     */
    @GetMapping("/provider/list")
    public ResponseEntity<ResponseDTO> selectAllProviderList() {

        List<ProviderDTO> providerList = memberService.selectAllProviderList();

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "제공자들 목록 조회 성공", providerList));
    }

    /**
     *Controller
     * Provider 제공자의 정보를  상세조회 합니다.
     * @param providerCode 제공자코드
     * @return 받은값 userCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/provider/detail/{providerCode}")
    public ResponseEntity<ResponseDTO> selectProviderDetailInfo(@PathVariable int providerCode) {


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "제공자 상세정보 조회 성공", memberService.selectProviderDetailInfo(providerCode)));
    }

    /**
     *Controller
     *Provider 제공자의 정보를 수정 합니다
     * @param providerDTO,providerCode  제공자 DTO 와 제공자코드
     * @return 받은값 providerDTO,providerCode 을 데이터에 담아 반환한다.
     */
    @PutMapping("/provider/detail/{providerCode}")
    public ResponseEntity<ResponseDTO> updateProviderDetailInfo(@RequestBody ProviderDTO providerDTO , @PathVariable int providerCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 정보 수정 성공",  memberService.updateProviderDetailInfo(providerDTO,providerCode)));
    }

    /**
     *Controller
     *Provider 제공자의 정보를 삭제 합니다
     * @param providerCode 제공자코드
     * @return 받은값 providerCode 을 데이터에 담아 반환한다.
     */
    @DeleteMapping("/provider/{providerCode}")
    public ResponseEntity<ResponseDTO> deleteProviderDetailInfo(@PathVariable int providerCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 탈퇴 성공",  memberService.deleteProviderDetailInfo(providerCode)));
    }

}
