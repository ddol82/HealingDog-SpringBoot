package com.healing.healingdog.mypage.controller;

import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.mypage.model.dto.MypetDTO;
import com.healing.healingdog.mypage.model.service.MypetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @param userCode 사용자의 코드
     * @return 받은값 userCode 을 데이터에 담아 반환한다.
     */
    @GetMapping("/products")
    public ResponseEntity<ResponseDTO> selectMyPetList(@RequestParam(name="userCode") int userCode) {

        List<MypetDTO> mypetList = mypetService.selectMyPetList(userCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "마이펫 목록 조회 성공", mypetList));
    }

}
