package com.healing.healingdog.community.controller;

import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.common.file.model.dto.ImageForm;
import com.healing.healingdog.common.paging.ItemWithPaging;
import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.common.paging.PageDataAutoFill;
import com.healing.healingdog.community.model.dto.BoardCreateDTO;
import com.healing.healingdog.community.model.dto.BoardTableDTO;
import com.healing.healingdog.community.model.dto.CatAndPageDataForBoard;
import com.healing.healingdog.community.model.dto.SimpleBoardDTO;
import com.healing.healingdog.community.model.service.CommunityService;
import com.healing.healingdog.community.model.type.BoardType;
import com.healing.healingdog.login.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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

        List<String> categories = communityService.selectCommunityCategoryList();

        log.info("[CommunityController] selectCommunityCategoryList 종료");
        String outputMessage = "카테고리 목록 " + categories.size() + "개 조회 완료";
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

        List<SimpleBoardDTO> boardList = boardDataConverter(boardTableList, false);

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

        List<SimpleBoardDTO> boardList = boardDataConverter(boardTableList, true);

        ItemWithPaging boardItem = new ItemWithPaging(pageData, boardList);
        String outputMessage = "카테고리 {" + boardType.getType() + "}의 " + pageData.getCurrPage() + "페이지 게시글 " + boardList.size() + "개 반환";

        log.info("[CommunityController] selectBoardList 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, boardItem));
    }

    /**
     * 내부적으로 사용하는 클래스로서, {@link BoardTableDTO} 형태로 조회한 데이터를<br>
     * {@link SimpleBoardDTO} 형태로 변환하고, {@link UserDTO User} 데이터를 덧붙입니다.
     * 2번째 인자값이 {@code true}인 경우 {@code image} 정보도 추가합니다.
     *
     * @param boardTableList {@link List}<{@link SimpleBoardDTO}> 타입으로 변환할 {@link List}<{@link BoardTableDTO}>입니다.
     * @param containsImage {@link Boolean boolean} 타입으로, {@code true}일 시 이미지 정보를 가져옵니다.
     * @return {@link BoardTableDTO}의 자료와 {@link UserDTO User}정보,
     * 선택적으로 {@code image} 정보를 {@link SimpleBoardDTO}타입으로 가공해 반환합니다.
     */
    private List<SimpleBoardDTO> boardDataConverter(List<BoardTableDTO> boardTableList, boolean containsImage) {
        log.info("[CommunityController] boardDataConverter 호출");
        List<SimpleBoardDTO> result = new ArrayList<>();
        for(BoardTableDTO boardTableItem : boardTableList) {
            log.debug("게시글 번호 " + boardTableItem.getBoardCode() + " 변환 시작");
            SimpleBoardDTO board = new SimpleBoardDTO();
            board.setBoard(boardTableItem);
            //content 미리보기 형으로 변환
            String contentPreview = board.getContent()
                    .replaceAll("(\r\n|\n\r|\n|\r|\t)", " ");
            board.setContent(contentPreview);
            //User - 더미데이터(수정필요)
            board.setUserNickname("test");
            board.setUserProfileImage(null);
            board.setUserProfileImageUrl(null);

            //Thumbnail
            if(containsImage) {
                board.setThumbnailImageUrl(communityService.selectBoardThumbnailUrl(board.getBoardCode()));
                board.setImageCount(communityService.selectBoardImageCount(board.getBoardCode()));
            }
            result.add(board);
            log.debug("게시글 번호 " + boardTableItem.getBoardCode() + " 변환 종료");
        }
        log.info("[CommunityController] boardDataConverter 종료");
        return result;
    }

    @PostMapping("/articles/write/confirm")
    public ResponseEntity<ResponseDTO> insertBoard(
            @RequestPart(value = "boardData") BoardCreateDTO boardCreateDTO,
            @RequestPart(value = "fileItems", required = false) List<ImageForm> fileItems,
            @RequestPart(value = "Images", required = false) List<MultipartFile> images) {
        log.info("[CommunityController] insertBoard 호출");

        for(int i = 0; i < fileItems.size(); i++) {
            fileItems.get(i).setImageFile(images.get(i));
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
}
