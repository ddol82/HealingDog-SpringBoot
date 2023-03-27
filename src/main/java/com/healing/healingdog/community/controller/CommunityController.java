package com.healing.healingdog.community.controller;

import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.common.paging.ItemWithPaging;
import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.common.paging.PageDataAutoFill;
import com.healing.healingdog.community.model.dto.BoardDTO;
import com.healing.healingdog.community.model.dto.DetailForGettingBoard;
import com.healing.healingdog.community.model.service.CommunityService;
import com.healing.healingdog.community.model.type.BoardType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 커뮤니티의 컨트롤러. {@link Slf4j @Slf4j}를 사용하여 {@code log}를 통한 logging 사용이 가능합니다.<br>
 * 다음의 기능을 포함하고 있습니다.
 * <ul>
 *     <li>{@link CommunityController#selectBoardList(String, int)} : 게시판 리스트 조회</li>
 * </ul>
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

    /**
     * 커뮤니티 내의 카테고리 목록을 조회합니다.
     * {@link CommunityService}를 이용하지 않고, {@link BoardType}을 순회하여
     * {@link BoardType#getName()} 값을 가져옵니다.
     * @return {@link List}<{@link String}>타입으로 카테고리 목록을 반환합니다.
     */
    @GetMapping("/lists/categories")
    public ResponseEntity<ResponseDTO> selectCommunityCategoryList() {
        log.info("[CommunityController] selectCommunityCategoryList 호출");

        List<String> categoryList = new ArrayList<>();
        for(BoardType boardType : BoardType.values()) {
            categoryList.add(boardType.getName());
        }
        String outputMessage = "카테고리 목록 " + categoryList.size() + "개 조회 완료";

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, categoryList));
    }

    /**
     * 커뮤니티 상단에 고정되어야 할 게시글들을
     * {@link CommunityService#selectBoardHeadline()}로 조회합니다.
     * @return 상단 고정 게시글들을 {@link List}<{@link BoardDTO}>타입으로 반환합니다.
     */
    @GetMapping("/lists/importants")
    public ResponseEntity<ResponseDTO> selectBoardHeadline() {
        log.info("[CommunityController] selectBoardHeadline 호출");

        List<BoardDTO> boardList = communityService.selectBoardHeadline();
        String outputMessage = "상단 고정의 게시글 " + boardList.size() + "개 반환";

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
    @GetMapping("/lists/boards")
    public ResponseEntity<ResponseDTO> selectBoardList(@RequestParam(name="cat", defaultValue = "all") String categoryType,
                                          @RequestParam(name="page", defaultValue = "1") int currPage) {
        log.info("[CommunityController] selectBoardList 호출");
        log.debug("(selectBoardList) categoryType : " + categoryType);
        log.debug("(selectBoardList) currPage : " + currPage);

        BoardType boardType = BoardType.getBoardType(categoryType);
        log.info("(selectBoardList) boardType : {}", boardType);

        int boardCountAll = communityService.selectBoardCountAll(boardType.getCode());
        PageData pageData = PageDataAutoFill.get(currPage, boardCountAll);
        log.info("(selectBoardList) pageData filled : {}", pageData);
        if(currPage < 1 || currPage > pageData.getEndPage()) {
            currPage = 1;
        }

        DetailForGettingBoard detailData = new DetailForGettingBoard(pageData, boardType);

        List<BoardDTO> boardList = new ArrayList<>();
        if(boardCountAll > 0) {
            boardList = communityService.selectBoardList(detailData);
        }
        ItemWithPaging boardItem = new ItemWithPaging(pageData, boardList);
        String outputMessage = "카테고리 {" + boardType.getType() + "}의 " + currPage + "페이지 게시글 List 반환";

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, boardItem));
    }
}
