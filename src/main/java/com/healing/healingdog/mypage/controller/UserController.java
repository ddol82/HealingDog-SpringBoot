package com.healing.healingdog.mypage.controller;


import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.login.model.dto.UserDTO;
import com.healing.healingdog.mypage.model.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mypage")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *Controller
     *User 사용자의 정보를 조회 합니다
     * @param userCode User의 번호
     * @return 받은값 userCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/user/{userCode}")
    public ResponseEntity<ResponseDTO> selectMyUserInfo(@PathVariable int userCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 조회 성공", userService.selectMyUserInfo(userCode)));
    }

    /**
     *Controller
     *User 사용자의 정보를 상세조회 합니다
     * @param userCode User의 번호
     * @return 받은값 userCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/user/detail/{userCode}")
    public ResponseEntity<ResponseDTO> selectMyUserDetailInfo(@PathVariable int userCode) {


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 상세정보조회 성공", userService.selectMyUserDetailInfo(userCode)));
    }

    /**
     *Controller
     *User 사용자의 정보를 수정(update) 합니다
     * @param userDTO,userCode  userDTO  userCode
     * @return 받은값 userDTO,userCode 을 데이터에 담아 반환한다.
     */
    @PutMapping("/user/detail/{userCode}")
    public ResponseEntity<ResponseDTO> updateMyUserDetailInfo(@RequestBody UserDTO userDTO , @PathVariable int userCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 정보 수정 성공",  userService.updateMyUserDetailInfo(userDTO,userCode)));
    }

    /**
     *Controller
     *User 사용자의 정보를 삭제(탈퇴 ) 합니다
     * @param userCode 삭제(탈퇴) 할 user를 userCode로
     * @return 받은값 userCode 을 데이터에 담아 반환한다.
     */
    @DeleteMapping("/user/detail/{userCode}")
    public ResponseEntity<ResponseDTO> deleteMyUserDetailInfo(@PathVariable int userCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 탈퇴 성공",  userService.deleteMyUserDetailInfo(userCode)));
    }

}
