package com.healing.healingdog.community.controller;

import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.common.file.model.dto.ImageFormDTO;
import com.healing.healingdog.common.paging.ItemWithPaging;
import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.common.paging.PageDataAutoFill;
import com.healing.healingdog.community.model.dto.*;
import com.healing.healingdog.community.model.service.CommunityService;
import com.healing.healingdog.community.model.type.BoardType;
import com.healing.healingdog.login.model.dto.UserDTO;
import com.healing.healingdog.membermanagement.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 커뮤니티의 컨트롤러. {@link Slf4j @Slf4j}를 사용하여 {@code log}를 통한 logging 사용이 가능합니다.
 *
 * @since 1.0
 * @version 1.0
 * @author 이진녕
 */
@Slf4j
@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;
    private final MemberService memberService;

    /**
     * 커뮤니티 내의 카테고리 목록을 조회합니다.<br>
     * {@link BoardType}을 순회하여
     * {@link BoardType#getName()} 값을 가져옵니다.
     * @return {@link List}<{@link String}>타입의 카테고리 값을
     * {@code data}에 담아 반환합니다.
     */
    @GetMapping("/lists/categories")
    public ResponseEntity<ResponseDTO> selectCommunityCategoryList() {
        log.info("[CommunityController] selectCommunityCategoryList 호출");

        List<CategotyDTO> categories = communityService.selectCommunityCategoryList();
        String outputMessage = "카테고리 목록 " + categories.size() + "개 조회 완료";

        log.info("[CommunityController] selectCommunityCategoryList 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, categories));
    }

    /**
     * 커뮤니티 상단에 고정되어야 할 게시글들을
     * {@link CommunityService#selectBoardHeadline()}로 조회합니다.
     * @return 상단 고정 게시글들을 {@link List}<{@link BoardTableDTO}>타입의
     * data에 담아 반환합니다.
     */
    @GetMapping("/lists/importants")
    public ResponseEntity<ResponseDTO> selectBoardHeadline() {
        log.info("[CommunityController] selectBoardHeadline 호출");

        List<BoardTableDTO> boardTableList = communityService.selectBoardHeadline();
        log.debug("상단 고정의 게시글 " + boardTableList.size() + "개 조회 완료");

        List<ResultBoardDTO> boardList = communityService.boardDataConverter(boardTableList);
        for(int i = 0; i < boardList.size(); i++) {
            log.info(boardList.get(i).toString());
            UserDTO user = memberService.selectUserDetailInfo(boardList.get(i).getUserCode());
            boardList.get(i).setProfileName(user.getNickname());
            boardList.get(i).setProfileImageUrl(null); //사진 구현 필요!!! @김선중
        }

        String outputMessage = "상단 고정의 게시글 " + boardList.size() + "개 반환";
        log.info("[CommunityController] selectBoardHeadline 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, boardList));
    }

    /**
     * 게시글 리스트를 조회합니다.<br>
     * Mapping: [<b>GET</b>] {@code /community/lists/boards?cat={}&page={}}
     *
     * @param categoryType 카테고리의 타입은 영어 소문자로만 구성되어있습니다.
     * @param currPage 현재 페이지를 입력받습니다.
     * @return 조건을 만족하는 게시글의 리스트를 {@link ItemWithPaging} 타입으로 출력합니다.<br>
     * 카테고리 값이 비정상적인 경우 <b>전체 게시글</b>을 조회합니다.<br>
     * 현재 페이지 값이 비정상적인 경우 <b>1</b>페이지로 이동합니다.
     */
    @GetMapping("/lists/boards/{cat}/{page}")
    public ResponseEntity<ResponseDTO> selectBoardList(@PathVariable(value = "cat") String categoryType,
                                          @PathVariable(value = "page") int currPage) {
        log.info("[CommunityController] selectBoardList 호출");
        if(categoryType == null) {
            categoryType = "all";
            log.debug("(selectBoardList) categoryType is null!!! it will be set \"all\"");
        }
        log.debug("(selectBoardList) categoryType : " + categoryType);
        log.debug("(selectBoardList) currPage : " + currPage);

        BoardType boardType = BoardType.getBoardType(categoryType);
        log.info("(selectBoardList) boardType : {}", boardType);

        int boardCountAll = communityService.selectBoardCountAll(boardType.getCode());
        PageData pageData = PageDataAutoFill.get(currPage, boardCountAll);
        log.info("(selectBoardList) pageData filled : {}", pageData);
        if(pageData.getCurrPage() > pageData.getEndPage()) {
            pageData = PageDataAutoFill.get(pageData.getEndPage(), boardCountAll);
        }
        if(pageData.getCurrPage() < 1) {
            pageData = PageDataAutoFill.get(1, boardCountAll);
        }

        CatAndPageDataForBoard detailData = new CatAndPageDataForBoard(pageData, boardType);
        List<BoardTableDTO> boardTableList = new ArrayList<>();
        if(boardCountAll > 0) {
            boardTableList = communityService.selectBoardList(detailData);
        }
        log.debug("boardTableList 조회 결과 : " + boardTableList.size());

        List<ResultBoardDTO> boardList = communityService.boardDataConverter(boardTableList);
        for(int i = 0; i < boardList.size(); i++) {
            log.info(boardList.get(i).toString());
            UserDTO user = memberService.selectUserDetailInfo(boardList.get(i).getUserCode());
            boardList.get(i).setProfileName(user.getNickname());
            boardList.get(i).setProfileImageUrl(null); //사진 구현 필요!!! @김선중
        }

        ItemWithPaging boardItem = new ItemWithPaging(pageData, boardList);
        String outputMessage = "카테고리 {" + boardType.getType() + "}의 " + pageData.getCurrPage() + "페이지 게시글 " + boardList.size() + "개 반환";

        log.info("[CommunityController] selectBoardList 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, boardItem));
    }

    @GetMapping("/boards/detail/get/{boardCode}")
    public ResponseEntity<ResponseDTO> selectBoardDetail(@AuthenticationPrincipal UserDTO currUser,
                                                         @PathVariable int boardCode) {
        log.info("[CommunityController] selectBoardDetail 호출");

        BoardTableDTO boardTableData = communityService.selectBoardDetail(boardCode);
        log.info(boardTableData.toString());
        ResultBoardDTO resultBoard = communityService.boardDataDetailConverter(boardTableData);
        log.info(resultBoard.toString());
        UserDTO user = memberService.selectUserDetailInfo(resultBoard.getUserCode());
        resultBoard.setProfileName(user.getNickname());
        resultBoard.setProfileImageUrl(null); //사진 구현 필요!!! @김선중
        Map<String, Integer> likeParamMap = new HashMap<>();
        if(currUser != null) {
            likeParamMap.put("userCode", currUser.getUserCode());
            likeParamMap.put("boardCode", boardCode);
            resultBoard.setLikeState(communityService.checkLikeState(likeParamMap));
        } else {
            resultBoard.setLikeState(0);
        }

        String outputMessage = "게시글 반환";
        log.info("[CommunityController] selectBoardDetail 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, resultBoard));
    }

    @PostMapping("/boards/write/confirm")
    public ResponseEntity<ResponseDTO> insertBoard(
            @RequestPart(value = "boardData") BoardCreateDTO boardCreateDTO,
            @RequestPart(value = "fileItems", required = false) List<ImageFormDTO> fileItems,
            @RequestPart(value = "Images", required = false) List<MultipartFile> images) {
        log.info("[CommunityController] insertBoard 호출");

        if(fileItems != null) {
            for(int i = 0; i < fileItems.size(); i++) {
                fileItems.get(i).setImageFile(images.get(i));
            }
        }
        boardCreateDTO.setFileItems(fileItems);

        boardCreateDTO = communityService.insertBoard(boardCreateDTO);
        String resultBoard = boardCreateDTO.getId() + "번 게시글이 등록되었습니다.";
        log.debug("[CommunityController] resultBoard 결과 출력 : {}", resultBoard);
        List<String> resultImage = new ArrayList<>();
        if(boardCreateDTO.getFileItems() != null && boardCreateDTO.getFileItems().size() > 0) {
            resultImage = communityService.insertBoardImage(boardCreateDTO);
            log.debug("[CommunityController] resultImage 결과 출력 : {}건", resultImage.size());
        }
        String outputMessage = "반환 결과는 다음과 같습니다.";
        List<String> outputData = communityService.insertBoardResult(resultBoard, resultImage);

        log.info("[CommunityController] insertBoard 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, outputData));
    }

    /**
     * 게시글 조회 시 조회 수를 1 올립니다.
     *
     * @param boardCode 조회 수가 올라갈 대상 게시글입니다.
     * @return 성공 시 1이 출력됩니다.
     */
    @PostMapping("/boards/details/view/{boardCode}")
    public ResponseEntity<ResponseDTO> viewIncrement(@PathVariable int boardCode) {
        log.info("[CommunityController] viewIncrement 호출");

        int result = communityService.viewIncrement(boardCode);

        String outputMessage = "반환 결과는 다음과 같습니다.";
        log.info("[CommunityController] viewIncrement 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, result));

    }
}
