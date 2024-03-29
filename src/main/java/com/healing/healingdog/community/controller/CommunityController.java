package com.healing.healingdog.community.controller;

import com.healing.healingdog.common.ResponseDTO;
import com.healing.healingdog.common.file.model.dto.ImageFormDTO;
import com.healing.healingdog.common.paging.ItemWithPaging;
import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.common.paging.PageDataAutoFill;
import com.healing.healingdog.community.model.dto.*;
import com.healing.healingdog.community.model.service.CommunityService;
import com.healing.healingdog.community.model.type.BoardType;
import com.healing.healingdog.exception.UserNotFoundException;
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
        for(int i = 0; i < boardTableList.size(); i++) {
            boardTableList.get(i).setLike(
                    communityService.selectAllLikeActivityDetail(
                            boardTableList.get(i).getBoardCode()));
            boardTableList.get(i).setCommentCount(
                    communityService.selectAllCommentActivityDetail(
                            boardTableList.get(i).getBoardCode()));
        }
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

        Map<String, Integer> boardListParams = new HashMap<>();
        boardListParams.put("categoryCode", BoardType.getBoardType(categoryType).getCode());
        boardListParams.put("limitParam", Math.min(pageData.getEndCursor() - pageData.getStartCursor() + 1, 10));
        boardListParams.put("offsetParam", pageData.getStartCursor() - 1);
        List<BoardTableDTO> boardTableList = new ArrayList<>();
        if(boardCountAll > 0) {
            boardTableList = communityService.selectBoardList(boardListParams);
            for(int i = 0; i < boardTableList.size(); i++) {
                boardTableList.get(i).setLike(
                        communityService.selectAllLikeActivityDetail(
                                boardTableList.get(i).getBoardCode()));
                boardTableList.get(i).setCommentCount(
                        communityService.selectAllCommentActivityDetail(
                                boardTableList.get(i).getBoardCode()));
            }
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

    /**
     * 다음 프로세스를 통해 게시글의 상세 정보를 불러옵니다
     * <ul>
     *     <li>{@link CommunityService#selectBoardDetail}<br>게시글 번호에 맞는 게시글 정보를 불러옵니다.</li>
     *     <li>{@link CommunityService#boardDataDetailConverter}<br>다른 정보가 포함된 DTO로 데이터를 옮깁니다.</li>
     *     <li>{@link MemberService#selectUserDetailInfo}<br>유저 코드를 통해 유저 정보를 얻습니다.</li>
     *     <li>{@link CommunityService#checkLikeState}<br>유저가 이미 좋아요를 한 글인지 확인합니다.</li>
     * </ul>
     * * 좋아요 수와 공유 수 조회는 포함되어있지 않습니다.
     *
     * @param currUser 현재 접속 중인 유저 정보를 토큰 기반으로 호출합니다.
     * @param boardCode 상세보기 게시글의 번호입니다.
     * @return 게시글 정보, 유저 정보, 좋아요 정보, 공유 정보가 반환됩니다.
     */
    @GetMapping("/boards/detail/get/{boardCode}")
    public ResponseEntity<ResponseDTO> selectBoardDetail(@AuthenticationPrincipal UserDTO currUser,
                                                         @PathVariable int boardCode) {
        log.info("[CommunityController] selectBoardDetail 호출");

        BoardTableDTO boardTableData = communityService.selectBoardDetail(boardCode);
        boardTableData.setLike(
                communityService.selectAllLikeActivityDetail(
                        boardTableData.getBoardCode()));
        boardTableData.setCommentCount(
                communityService.selectAllCommentActivityDetail(
                        boardTableData.getBoardCode()));
        log.info(boardTableData.toString());
        ResultBoardDTO resultBoard = communityService.boardDataDetailConverter(boardTableData);
        log.info(resultBoard.toString());
        UserDTO user = memberService.selectUserDetailInfo(resultBoard.getUserCode());
        resultBoard.setProfileName(user.getNickname());
        resultBoard.setProfileImageUrl(null); //사진 구현 필요!!! @김선중

        Map<String, Integer> likeParamMap = new HashMap<>();
        if(currUser != null) {
            resultBoard.setIsAuthor(user.getUserCode() == currUser.getUserCode() ? 1 : 0);
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

    /**
     * 게시글의 좋아요 수, 공유 수, 댓글 수를 모두 조회합니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 좋아요 수, 공유 수, 댓글 수를 담은 {@link Map}을 반환합니다.
     */
    @GetMapping("/boards/details/activities/{boardCode}")
    public ResponseEntity<ResponseDTO> selectAllActivityDetail(@PathVariable int boardCode){
        log.info("[CommunityController] selectAllActivityDetail 호출");

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("like", communityService.selectAllLikeActivityDetail(boardCode));
        log.debug("selectAllActivityDetail: 좋아요 수 조회 완료");
        resultMap.put("share", communityService.selectAllShareActivityDetail(boardCode));
        log.debug("selectAllActivityDetail: 공유 수 조회 완료");
        resultMap.put("comment", communityService.selectAllCommentActivityDetail(boardCode));
        log.debug("selectAllActivityDetail: 댓글 수 조회 완료");
        String outputMessage = "좋아요/공유/댓글 수 반환";
        log.info("[CommunityController] selectAllActivityDetail 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, resultMap));
    }

    /**
     * 게시글에 맞는 댓글을 모두 조회합니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 댓글 목록을 {@link List}<{@link CommentDTO}>타입으로 반환합니다.
     */
    @GetMapping("/lists/comments/{boardCode}")
    public ResponseEntity<ResponseDTO> selectAllComments(@AuthenticationPrincipal UserDTO user,
                                                         @PathVariable int boardCode){
        log.info("[CommunityController] selectAllComments 호출");

        List<CommentDTO> commentList = communityService.selectAllComments(user.getUserCode(), boardCode);

        String outputMessage = "댓글 반환";
        log.info("[CommunityController] selectAllComments 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, commentList));
    }

    /**
     * 게시글을 작성합니다.
     *
     * @param user 현재 접속한 사용자 정보입니다.
     * @param boardData 게시글의 정보가 일부 담겨있습니다.
     * @param images 폼 데이터로 받은 사진 파일의 {@link List}입니다.
     * @return 삽입 결과를 반환합니다.
     */
    @PostMapping("/boards/write/confirm")
    public ResponseEntity<ResponseDTO> insertBoard(
            @AuthenticationPrincipal UserDTO user,
            @RequestPart BoardCreateDTO boardData,
            @RequestPart(required = false) List<MultipartFile> images) {
        log.info("[CommunityController] insertBoard 호출");

        List<ImageFormDTO> imageDetail = new ArrayList<>();
        if(images != null && images.size() > 0) {
            for(int i = 0; i < images.size(); i++) {
                imageDetail.add(new ImageFormDTO());
                imageDetail.get(i).setUsage(i+"");
            }
            for(int i = 0; i < imageDetail.size(); i++) {
                imageDetail.get(i).setImageFile(images.get(i));
                //imageDetail.get(i).setHasThumbnail("X");
            }
            //imageDetail.get(0).setHasThumbnail("O");
        }
        boardData.setUserCode(user.getUserCode());
        boardData.setFileItems(imageDetail);

        boardData = communityService.insertBoard(boardData);
        String resultBoard = boardData.getId() + "번 게시글이 등록되었습니다.";
        log.debug("[CommunityController] resultBoard 결과 출력 : {}", resultBoard);
        log.info("[CommunityController] 이미지 작업 시작");
        List<String> resultImage = new ArrayList<>();
        if(boardData.getFileItems() != null && boardData.getFileItems().size() > 0) {
            resultImage = communityService.insertBoardImage(boardData);
            log.debug("[CommunityController] resultImage 결과 출력 : {}건", resultImage.size());
        }
        String outputMessage = "반환 결과는 다음과 같습니다.";
        List<String> outputData = communityService.insertBoardResult(resultBoard, resultImage);

        log.info("[CommunityController] insertBoard 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, outputData));
    }

    /**
     * 댓글을 작성합니다.
     *
     * @param boardCode 댓글을 작성할 대상 게시글입니다.
     * @return 성공 시 1이 출력됩니다.
     */
    @PostMapping("/write/comments/{boardCode}/{refCode}")
    public ResponseEntity<ResponseDTO> registComment(@AuthenticationPrincipal UserDTO user,
                                                     @PathVariable int boardCode,
                                                     @PathVariable int refCode,
                                                     @RequestBody String content) {
        log.info("[CommunityController] registComment 호출");

        Map<String, String> commentParams = new HashMap<>();
        commentParams.put("boardCode", boardCode+"");
        commentParams.put("userCode", user.getUserCode()+"");
        commentParams.put("ref", refCode+"");
        commentParams.put("content", content);
        int result = communityService.registComment(commentParams);

        String outputMessage = "등록 결과는 다음과 같습니다.";
        log.info("[CommunityController] registComment 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, result));
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

    /**
     * 게시글의 좋아요 수가 +1 또는 -1되는 것을 반영합니다.
     *
     * @param boardCode 공유 수가 올라갈 대상 게시글입니다.
     * @return 성공 시 1이 출력됩니다.
     */
    @PostMapping("/boards/details/like/{boardCode}/{likeStatus}")
    public ResponseEntity<ResponseDTO> likeChange(@AuthenticationPrincipal UserDTO user,
                                                  @PathVariable int boardCode,
                                                  @PathVariable String likeStatus) {
        log.info("[CommunityController] likeChange 호출");
        log.info(String.valueOf(user));
        Map<String, Integer> likeParams = new HashMap<>();
        likeParams.put("userCode", user.getUserCode());
        likeParams.put("boardCode", boardCode);

        int result = 0;
        if(likeStatus.equals("up")) {
            result += communityService.insertLikeChange(likeParams);
        } else if(likeStatus.equals("down")) {
            result += communityService.deleteLikeChange(likeParams) * -1;
        }

        String outputMessage = "반환 결과는 다음과 같습니다.";
        log.info("[CommunityController] likeChange 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, result));
    }

    /**
     * 게시글의 공유 수를 1 올립니다.
     *
     * @param boardCode 공유 수가 올라갈 대상 게시글입니다.
     * @return 성공 시 1이 출력됩니다.
     */
    @PostMapping("/boards/details/share/{boardCode}")
    public ResponseEntity<ResponseDTO> shareIncrement(@PathVariable int boardCode) {
        log.info("[CommunityController] shareIncrement 호출");

        int result = communityService.shareIncrement(boardCode);

        String outputMessage = "반환 결과는 다음과 같습니다.";
        log.info("[CommunityController] shareIncrement 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, result));
    }

    /**
     * 게시글의 정보를 수정합니다.<br>
     * 사진의 경우, 다음 절차를 수행하여 파일과 DB를 저장합니다.<br>
     * <ul>
     *     <li>변경 사항이 없는 경우 건너뜁니다.</li>
     *     <li>새로 첨부된 경우 사진 파일 및 DB의 추가를 진행합니다.</li>
     *     <li>위치가 변경된 경우 DB의 수정이 진행됩니다.</li>
     *     <li>기존 사진이 삭제된 경우 파일을 삭제합니다.</li>
     * </ul>
     *
     * @param user 현재 접속한 유저 정보입니다.
     * @param boardData 카테고리, 제목, 본문, 게시글id, 사진(용량/위지/기존여부)정보
     *                  {@link List}를 입력받습니다.
     * @param images 파일 정보를 입력받습니다.
     * @return 게시글 수정 수행 결과를 반환합니다.
     */
    @PutMapping("/boards/update/confirm/{boardCode}")
    public ResponseEntity<ResponseDTO> updateBoard(
            @AuthenticationPrincipal UserDTO user,
            @RequestPart BoardUpdateDTO boardData,
            @RequestPart(required = false) List<MultipartFile> images) {
        log.info("[CommunityController] updateBoard 호출");

        boardData.setUserCode(user.getUserCode());
        log.debug("[CommunityController] boardData : {}", boardData);
        //ex: BoardUpdateDTO(id=null, boardCode=110, userCode=0, boardCategoryCode=8, title=udpat3e, content=updaete, beforeContains=5,
        //    position=[0, 2, 1, 3, -1, 4], size=[7796963, 2915658, 2386155, 1660972, 1515927, 3192464], fileItems=null)
        //게시글 반영
        BoardCreateDTO boardUpdate = new BoardCreateDTO();
        boardUpdate.setBoardCode(boardData.getBoardCode());
        boardUpdate.setBoardCategoryCode(boardData.getBoardCategoryCode());
        boardUpdate.setTitle(boardData.getTitle());
        boardUpdate.setContent(boardData.getContent());
        communityService.updateBoard(boardUpdate);

        //사진 처리
        int imageAmount = boardData.getPosition().size();
        int imageCursor = 0;
        //결과변수 정의
        int resultAdd = 0;
        List<String> resultAddedImageName = new ArrayList<>();
        int resultRemove = 0;
        int resultModify = 0;
        for(int i = 0; i < imageAmount; i++) {
            Map<String, String> moveParams = new HashMap<>();
            //변경 사항이 없는 사진 유지
            if(boardData.getPosition().get(i) == i) {
                moveParams.put("boardCode", boardData.getBoardCode()+"");
                moveParams.put("beforeIdx", i+"");
                moveParams.put("afterIdx", (char) ('A' + i)+""); // 반영 끝내고 -'A' 연산
                communityService.updateBoardImageUsage(moveParams);
                log.info("[CommunityController] " + i + "번째 요청을 건너뛰었습니다.");
                continue;
            }
            //새로 첨부된 사진 추가
            if(boardData.getPosition().get(i) == -1) {
                log.info("[CommunityController] " + i + "번째 요청으로 사진을 삽입합니다.");
                BoardCreateDTO boardCreate = new BoardCreateDTO();
                boardCreate.setId(boardData.getBoardCode()+"");
                boardCreate.setUserCode(boardData.getUserCode());
                boardCreate.setBoardCategoryCode(boardData.getBoardCategoryCode());
                boardCreate.setTitle(boardData.getTitle());
                boardCreate.setContent(boardData.getContent());

                List<Integer> singleSize = new ArrayList<>();
                singleSize.add(boardData.getSize().get(i));
                boardCreate.setSize(singleSize);

                List<ImageFormDTO> imageForm = new ArrayList<>();
                imageForm.add(new ImageFormDTO());
                imageForm.get(0).setImageFile(images.get(imageCursor++));
                imageForm.get(0).setUsage((char) ('A' + i)+""); // 반영 끝내고 -'A' 연산
                //imageForm.get(0).setHasThumbnail(i==0 ? "O" : "X");
                boardCreate.setFileItems(imageForm);
                List<String> result = communityService.insertBoardImage(boardCreate);
                resultAdd += 1;
                resultAddedImageName.addAll(result);
                continue;
            }
            //파일은 남아있으나 위치가 변경된 사진 수정
            log.info("[CommunityController] " + i + "번째 요청으로 사진 위치를 변경합니다.");
            moveParams.put("boardCode", boardData.getBoardCode()+"");
            moveParams.put("beforeIdx", boardData.getPosition().get(i)+"");
            moveParams.put("afterIdx", (char) ('A' + i)+""); // 반영 끝내고 -'A' 연산
            communityService.updateBoardImageUsage(moveParams);
        }
        //스캔된 파일은 usage가 +'A'된 상태, 스캔되지 않은 usage가 숫자인 파일 삭제
        communityService.deleteBoardImageUnused(boardData.getBoardCode());
        resultRemove += communityService.deleteBoardImageTableWithUsage(boardData.getBoardCode());

        //변경된 사진 usage는 문자상태, 문자 상태의 usage가 존재할 경우 숫자로 변환
        for(int i = 0; i < imageAmount; i++) {
            Map<String, String> moveParams = new HashMap<>();
            moveParams.put("boardCode", boardData.getBoardCode()+"");
            moveParams.put("beforeIdx", (char) ('A' + i)+""); // -'A' 연산
            moveParams.put("afterIdx", i+"");
            resultModify += communityService.updateBoardImageUsage(moveParams);
        }


        String outputMessage = "반환 결과는 다음과 같습니다.";
        StringBuilder resultData = new StringBuilder();
        resultData.append("추가된 사진 : ").append(resultAdd).append("\n");
        for(String str : resultAddedImageName) {
            resultData.append(str).append("\n");
        }
        resultData.append("수정된 사진 : ").append(resultModify)
                .append("삭제된 사진 : ").append(resultRemove);

        log.info("[CommunityController] updateBoard 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, resultData.toString()));
    }

    /**
     * 게시글 상세보기에서 게시글을 삭제하는 과정입니다.
     *
     * @param user 현재 유저의 토큰이 유효한지 검증하는 용도입니다.
     * @param boardCode 대상 게시글의 코드입니다.
     * @return 삭제 완료 시 1이 출력됩니다.
     */
    @DeleteMapping("boards/delete/{boardCode}")
    public ResponseEntity<ResponseDTO> deleteBoard(@AuthenticationPrincipal UserDTO user,
                                                   @PathVariable int boardCode) {
        log.info("[CommunityController] deleteBoard 호출");

        if(user == null) throw new UserNotFoundException("현재 사용자 정보를 찾을 수 없습니다.");
        int[] result = new int[4];
        result[0] = communityService.deleteBoard(boardCode);
        log.info("[CommunityController] board 종료 완료, 사진 삭제 진행");
        result[1] = communityService.deleteBoardImage(boardCode);
        log.info("[CommunityController] board images 삭제 완료, 사진 DB 삭제 진행");
        result[2] = communityService.deleteBoardImageTable(boardCode);
        log.info("[CommunityController] 사진 DB 삭제 완료, 좋아요 관계 삭제 진행");
        result[3] = communityService.deleteAllLikeChange(boardCode);
        log.info("[CommunityController] 좋아요 관계 삭제 완료");

        String outputMessage = "삭제 완료된 결과는 다음과 같습니다.";
        log.info("[CommunityController] deleteBoard 종료");
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, outputMessage, result));
    }
}
