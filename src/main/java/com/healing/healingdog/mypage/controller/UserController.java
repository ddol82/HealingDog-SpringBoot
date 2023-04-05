package com.healing.healingdog.mypage.controller;


import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.login.model.dto.UserDTO;
import com.healing.healingdog.mypage.model.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mypage")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @GetMapping("/user/{userCode}")
//    public ResponseEntity<ResponseDTO> selectMyUserInfo(@PathVariable int userCode) {
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 조회 성공", userService.selectMyUserInfo(userCode)));
//    }
    /**
     *Controller
     *User 사용자의 정보를 조회 합니다
     * @param user 사용자코드
     * @return 받은값 userCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/user")
    public ResponseEntity<ResponseDTO> selectMyUserInfo(@AuthenticationPrincipal UserDTO user) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 조회 성공", userService.selectMyUserInfo(user.getUserCode())));
    }


    /**
     *Controller
     *User 사용자의 정보를 상세조회 합니다
     * @param user 사용자코드
     * @return 받은값 userCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/user/detail")
    public ResponseEntity<ResponseDTO> selectMyUserDetailInfo(@AuthenticationPrincipal UserDTO user) {


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 상세정보조회 성공", userService.selectMyUserDetailInfo(user.getUserCode())));
    }

    /**
     *Controller
     *User 사용자의 정보를 수정(update) 합니다
     * @param userDTO,user  userDTO  ,사용자코드
     * @return 받은값 userDTO,userCode 을 데이터에 담아 반환한다.
     */
    @PutMapping("/user/detail")
    public ResponseEntity<ResponseDTO> updateMyUserDetailInfo(@RequestBody UserDTO userDTO , @AuthenticationPrincipal UserDTO user) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사용자 정보 수정 성공",  userService.updateMyUserDetailInfo(userDTO,user.getUserCode())));
    }

    /**
     *Controller
     *User 사용자의 정보를 삭제(탈퇴 ) 합니다
     * @param user 사용자코드
     * @return 받은값 userCode 을 데이터에 담아 반환한다.
     */
    @DeleteMapping("/user/detail")
    public ResponseEntity<ResponseDTO> deleteMyUserDetailInfo(@AuthenticationPrincipal UserDTO user) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 탈퇴 성공",  userService.deleteMyUserDetailInfo(user.getUserCode())));
    }

}
