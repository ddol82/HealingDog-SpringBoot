package com.healing.healingdog.community.model.service;

import com.healing.healingdog.common.file.model.dto.ImageFormDTO;
import com.healing.healingdog.common.file.model.dto.ImageTableDTO;
import com.healing.healingdog.common.file.model.dto.ImageType;
import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.common.util.ImageUtils;
import com.healing.healingdog.community.model.dao.CommunityMapper;
import com.healing.healingdog.community.model.dto.*;
import com.healing.healingdog.community.model.type.BoardType;
import com.healing.healingdog.login.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 커뮤니티의 서비스. {@link Slf4j @Slf4j}를 사용하여 {@code log}를 통한 logging 사용이 가능합니다.
 *
 * @since 1.0
 * @version 1.0
 * @author 이진녕
 */
@Slf4j
@Service
public class CommunityService {
    @Value("${image.add-resource-locations}")
    private String IMAGE_DIR_PREFIX;
    @Value("${image.image-url-prefix}")
    private String IMAGE_URL_PREFIX;
    @Value("${image.type.board}")
    private String IMAGE_TYPE;
    private final CommunityMapper communityMapper;

    public CommunityService(CommunityMapper communityMapper) {
        this.communityMapper = communityMapper;
    }
    /**
     * 커뮤니티 내의 카테고리 목록을 조회합니다.<br>
     * {@link BoardType}을 순회하여
     * {@link BoardType#getName()} 값을 가져옵니다.
     * @return {@link List}<{@link String}>타입으로 카테고리 목록을 반환합니다.
     */
    public List<CategotyDTO> selectCommunityCategoryList() {
        List<CategotyDTO> categoryList = new ArrayList<>();
        for(BoardType boardType : BoardType.values()) {
            categoryList.add(new CategotyDTO(
                    boardType.getCode(),
                    boardType.getType(),
                    boardType.getName()
            ));
        }
        return categoryList;
    }

    /**
     * 커뮤니티 상단에 고정되어야 하는 게시글을 조회합니다.
     * @return 조회된 게시글을 {@link List}형태로 반환합니다.
     */
    public List<BoardTableDTO> selectBoardHeadline() {
        log.info("[CommunityService] selectBoardHeadline 호출");

        List<BoardTableDTO> boardList = communityMapper.selectBoardHeadline();
        log.debug("(selectBoardCount) 조회 결과 : " + boardList.size() + "건");

        log.info("[CommunityService] selectBoardHeadline 종료");
        return boardList;
    }

    /**
     * 카테고리에 맞는 게시글의 수를 조회합니다.
     * @param categoryCode {@link BoardType}의 {@link BoardType#getCode() code}입니다. 카테고리가 {@link BoardType#ALL ALL}인 경우,
     * 모든 게시글을 조회합니다.
     * @return 조회된 게시글의 수를 반환합니다.
     */
    public int selectBoardCountAll(int categoryCode) {
        log.info("[CommunityService] selectBoardCount 호출");

        int count = communityMapper.selectBoardCountAll(categoryCode);
        log.debug("(selectBoardCount) 조회 결과 : " + count);

        log.info("[CommunityService] selectBoardCount 종료");
        return count;
    }

    /**
     * 조건에 맞는 게시글을 {@link List}로 조회합니다.
     * @param detailData {@link CatAndPageDataForBoard} 타입을 사용하며, 게시글의 인덱스 정보와 카테고리 코드 정보를 가지고 있습니다.
     * @return 카테고리 타입이 일치하고, {@link PageData#getStartCursor 시작 인덱스}와 {@link PageData#getEndCursor() 끝 인덱스}
     * 사이에 있는 게시글들을<br> {@link List}<{@link BoardTableDTO}>타입으로 반환합니다.
     */
    public List<BoardTableDTO> selectBoardList(CatAndPageDataForBoard detailData) {
        log.info("[CommunityService] selectBoardList 호출");

        List<BoardTableDTO> boardList= communityMapper.selectBoardList(detailData);
        log.debug("(selectBoardCount) 조회 결과 : " + boardList.size() + "건");

        log.info("[CommunityService] selectBoardList 종료");
        return boardList;
    }

    /**
     * {@link BoardTableDTO} 형태로 조회한 데이터를<br>
     * {@link ResultBoardDTO} 형태로 변환하고, {@link UserDTO User} 데이터를 덧붙입니다.
     * 2번째 인자값이 {@code true}인 경우 {@code image} 정보도 추가합니다.
     * 리스트로 표시할 간략한 버전입니다.
     *
     * @param boardTableList {@link List}<{@link ResultBoardDTO}> 타입으로 변환할 {@link List}<{@link BoardTableDTO}>입니다.
     * @return {@link BoardTableDTO}의 자료와 {@link UserDTO User}정보,
     * 선택적으로 {@code image} 정보를 {@link ResultBoardDTO}타입으로 가공해 반환합니다.
     */
    public List<ResultBoardDTO> boardDataConverter(List<BoardTableDTO> boardTableList) {
        log.info("[CommunityController] boardDataConverter 호출");
        List<ResultBoardDTO> result = new ArrayList<>();
        for(BoardTableDTO boardTableItem : boardTableList) {
            log.debug("게시글 번호 " + boardTableItem.getBoardCode() + " 변환 시작");
            ResultBoardDTO board = new ResultBoardDTO();
            board.setBoard(boardTableItem);
            //content 미리보기 형으로 변환
            String contentPreview = board.getContent()
                    .replaceAll("(\r\n|\n\r|\n|\r|\t)", " ");
            if(contentPreview.length() > 300) {
                contentPreview = contentPreview.substring(0, 300);
            }
            board.setContent(contentPreview);
            board.setUserCode(boardTableItem.getUserCode());
            board.setThumbnailImageUrl(selectBoardThumbnailUrl(board.getBoardCode()));
            board.setImageCount(selectBoardImageCount(board.getBoardCode()));

            result.add(board);
            log.debug("게시글 번호 " + boardTableItem.getBoardCode() + " 변환 종료");
        }
        log.info("[CommunityController] boardDataConverter 종료");
        return result;
    }

    /**
     * 단일 {@link BoardTableDTO}를 단일 {@link ResultBoardDTO} 타입으로 변환합니다.
     *
     * @param boardTableData {@code boards} 테이블에서 받아온 정보입니다.
     * @return 사진을 포함한 게시글의 정보가 반환됩니다.
     */
    public ResultBoardDTO boardDataDetailConverter(BoardTableDTO boardTableData) {
        log.debug("게시글 번호 " + boardTableData.getBoardCode() + " 변환 시작");

        ResultBoardDTO result = new ResultBoardDTO();
        result.setBoard(boardTableData);
        result.setUserCode(boardTableData.getUserCode());
        result.setOriginalImageUrl(selectBoardOriginalUrl(boardTableData.getBoardCode()));
        result.setPreviewImageUrl(selectBoardPreviewUrl(boardTableData.getBoardCode()));
        result.setImageCount(selectBoardImageCount(boardTableData.getBoardCode()));

        log.debug("게시글 번호 " + boardTableData.getBoardCode() + " 변환 종료");
        return result;
    }

    /**
     * 대상 게시글의 원본 이미지들을 불러옵니다.
     *
     * @param boardCode 게시글 번호를 입력받습니다.
     * @return 이미지 {@link List}를 불러옵니다.
     */
    private List<String> selectBoardOriginalUrl(int boardCode) {
        log.info("[CommunityService] selectBoardOriginalUrl 호출");

        List<String> originalUrl = communityMapper.selectBoardOriginalUrl(boardCode);
        log.debug("(selectBoardOriginalUrl) 조회 결과 : " + originalUrl.size() + "건");

        log.info("[CommunityService] selectBoardOriginalUrl 종료");
        return originalUrl;
    }

    /**
     * 대상 게시글의 썸네일 경로를 조회합니다.
     *
     * @param boardCode 대상 게시글의 PK입니다.
     * @return 썸네일의 경로가 {@link String}타입으로 반환됩니다.
     */
    private String selectBoardThumbnailUrl(int boardCode) {
        log.info("[CommunityService] selectBoardThumbnailUrl 호출");

        String thumbnailUrl = communityMapper.selectBoardThumbnailUrl(boardCode);
        log.debug("(selectBoardThumbnailUrl) 조회 결과 : " + (thumbnailUrl != null ? thumbnailUrl : "없음 - null 반환"));

        log.info("[CommunityService] selectBoardThumbnailUrl 종료");
        return thumbnailUrl;
    }

    /**
     * 대상 게시글의 미리보기(64 x 64) 이미지들을 불러옵니다.
     *
     * @param boardCode 게시글 번호를 입력받습니다.
     * @return 이미지 {@link List}를 불러옵니다.
     */
    private List<String> selectBoardPreviewUrl(int boardCode) {
        log.info("[CommunityService] selectBoardPreviewUrl 호출");

        List<String> previewUrl = communityMapper.selectBoardPreviewUrl(boardCode);
        log.debug("(selectBoardOriginalUrl) 조회 결과 : " + previewUrl.size() + "건");

        log.info("[CommunityService] selectBoardPreviewUrl 종료");
        return previewUrl;
    }

    /**
     * 대상 게시글의 이미지 개수를 조회합니다.
     *
     * @param boardCode 대상 게시글의 PK입니다.
     * @return 게시글 내 모든 이미지 수를 반환됩니다.
     */
    private int selectBoardImageCount(int boardCode) {
        log.info("[CommunityService] selectBoardImageCount 호출");

        int count = communityMapper.selectBoardImageCount(boardCode);
        log.debug("(selectBoardImageCount) 조회 결과 : " + count + "건");

        log.info("[CommunityService] selectBoardImageCount 종료");
        return count;
    }

    public BoardTableDTO selectBoardDetail(int boardCode) {
        log.info("[CommunityService] selectBoardDetail 호출");
        BoardTableDTO boardTableData = communityMapper.selectBoardDetail(boardCode);
        log.info("[CommunityService] selectBoardDetail 종료");
        return boardTableData;
    }

    /**
     * 게시글의 좋아요 수를 조회합니다.
     *
     * @param boardCode 대상 게시글의 코드입니다.
     * @return 게시글의 좋아요 수가 반환됩니다.
     */
    public int selectAllLikeActivityDetail(int boardCode) {
        log.info("[CommunityService] selectAllLikeActivityDetail 호출");
        int result = communityMapper.selectAllLikeActivityDetail(boardCode);
        log.info("[CommunityService] selectAllLikeActivityDetail 종료");
        return result;
    }

    /**
     * 게시글의 공유 수를 조회합니다.
     *
     * @param boardCode 대상 게시글의 코드입니다.
     * @return 게시글의 공유 수가 반환됩니다.
     */
    public int selectAllShareActivityDetail(int boardCode) {
        log.info("[CommunityService] selectAllShareActivityDetail 호출");
        int result = communityMapper.selectAllShareActivityDetail(boardCode);
        log.info("[CommunityService] selectAllShareActivityDetail 종료");
        return result;
    }

    /**
     * 게시글의 댓글 수를 조회합니다.
     *
     * @param boardCode 대상 게시글의 코드입니다.
     * @return 게시글의 댓글 수가 반환됩니다.
     */
    public int selectAllCommentActivityDetail(int boardCode) {
        log.info("[CommunityService] selectAllCommentActivityDetail 호출");
        int result = communityMapper.selectAllCommentActivityDetail(boardCode);
        log.info("[CommunityService] selectAllCommentActivityDetail 종료");
        return result;
    }

    /**
     * 작성된 게시글을 등록합니다.
     *
     * @param boardCreateDTO 게시글의 정보가 담긴 DTO입니다.
     * @return 등록된 게시글의 id(PK)가 포함되어 반환됩니다.<br>
     * 등록 실패 시 {@code null}을 반환합니다.
     */
    public BoardCreateDTO insertBoard(BoardCreateDTO boardCreateDTO) {
        log.info("[CommunityService] insertBoard 호출");

        try {
            communityMapper.insertBoard(boardCreateDTO);
            /*
             * Mapper Interface에서 void형으로 반환하고
             * Mapper xml에 useGeneratedKeys, keyProperty 속성을 지정하면
             * parameter로 쓰인 boardCreateDTO에
             * PK값인 id가 저장됩니다.
             */
        } catch (Exception e) {
            log.warn("[CommunityService] 예외 상황 발생 : ", e);
        }

        log.info("[CommunityService] insertBoard 종료");
        return boardCreateDTO;
    }

    /**
     * 게시글의 이미지 정보를 board_images 테이블에 담습니다.
     *
     * @param boardCreateDTO 파일이 포함된 DTO입니다.
     * @return 이미지 등록 결과를 {@link List}<{@link String}>타입으로 반환합니다.
     */
    @Transactional
    public List<String> insertBoardImage(BoardCreateDTO boardCreateDTO) {
        log.info("[CommunityService] insertBoardImage 호출");

        String direction = IMAGE_DIR_PREFIX + IMAGE_TYPE;
        List<String> result = new ArrayList<>();
        List<ImageFormDTO> files = boardCreateDTO.getFileItems();
        for(ImageFormDTO imageFile : files) {
            try {
                log.info("[CommunityService] 이미지 1건의 저장을 시도합니다.");
                String original = ImageUtils.saveImage(direction, imageFile.getImageFile());
                String thumbnail = null;
                if(imageFile.getHasThumbnail().equals("O")) {
                    log.info("[CommunityService] 썸네일을 포함해서요!");
                    int resizeWidth = 140;
                    int resizeHeight = 140;
                    thumbnail = ImageUtils.saveThumbnail(direction, imageFile.getImageFile(), resizeWidth, resizeHeight);
                }
                log.info("[CommunityService] 미리보기(64 x 64)를 생성합니다.");
                int resizeWidth = 64;
                int resizeHeight = 64;
                String preview = ImageUtils.saveThumbnail(direction, imageFile.getImageFile(), resizeWidth, resizeHeight);

                ImageTableDTO imageTableDTO = new ImageTableDTO();
                imageTableDTO.setImageType(ImageType.BOARD);
                imageTableDTO.setRefCode(boardCreateDTO.getId());
                imageTableDTO.setUsage(imageFile.getUsage());
                imageTableDTO.setOriginal(original);
                imageTableDTO.setThumbnail(thumbnail);
                imageTableDTO.setPreview(preview);
                communityMapper.insertBoardImage(imageTableDTO);

                result.add("original - " + original);
                if(thumbnail != null) {
                    result.add("thumbnail - " + thumbnail);
                }
                result.add("preview - " + preview);
                log.info("[CommunityService] 이미지 1건의 저장이 끝났습니다.");
            } catch (IOException e) {
                log.error("이미지 저장 중 오류가 발생했습니다.");
            }
        }

        log.info("[CommunityService] insertBoardImage 종료");
        return result;
    }

    /**
     * 게시글의 등록 결과와 게시글 사진의 등록 결과를 합쳐<br>
     * 하나의 {@link List}<{@link String}>으로 만듭니다.<br>
     * 게시글 등록 실패 시 <b>실패 문구</b>가 포함된<br>
     * size 1의 {@link List}<{@link String}>을 반환합니다.
     *
     * @param resultBoard 게시글 등록 결과입니다.
     * @param resultImage 이미지 등록 결과입니다.
     * @return 합쳐진 {@link String}들의 {@link List}를 반환합니다.
     */
    public List<String> insertBoardResult(String resultBoard, List<String> resultImage) {
        log.info("[CommunityService] insertBoardResult 호출");
        List<String> result = new ArrayList<>();

        if(resultBoard == null) {
            result.add("(fail) 게시글을 등록할 수 없었습니다.");
            return result;
        }
        result.add(resultBoard);
        result.addAll(resultImage);

        log.info("[CommunityService] insertBoardResult 종료");
        return result;
    }

    /**
     * 게시글의 조회 수를 1 올립니다.
     *
     * @param boardCode 대상 게시글입니다.
     * @return 성공 시 1이 반환됩니다.
     */
    public int viewIncrement(int boardCode) {
        log.info("[CommunityService] viewIncrement 호출");
        int result = communityMapper.viewIncrement(boardCode);
        log.info("[CommunityService] viewIncrement 종료");
        return result;
    }

    /**
     * 게시글의 공유 수를 1 올립니다.
     *
     * @param boardCode 대상 게시글입니다.
     * @return 성공 시 1이 반환됩니다.
     */
    public int shareIncrement(int boardCode) {
        log.info("[CommunityService] shareIncrement 호출");
        int result = communityMapper.shareIncrement(boardCode);
        log.info("[CommunityService] shareIncrement 종료");
        return result;
    }

    /**
     * 게시글 좋아요 여부를 확인합니다.
     *
     * @param likeParamMap 현재 로그인한 유저 코드, 게시글 코드
     * @return 기본 0, 좋아요가 되어있으면 1이 반환됩니다.
     */
    public int checkLikeState(Map<String, Integer> likeParamMap) {
        log.info("[CommunityService] checkLikeState 호출");
        log.info("userCode : " + likeParamMap.get("userCode"));
        log.info("boardCode : " + likeParamMap.get("boardCode"));
        int result = communityMapper.checkLikeState(likeParamMap);
        log.info("[CommunityService] checkLikeState 종료");
        return result;
    }

    /**
     * 좋아요를 등록 상태로 변환합니다.
     *
     * @param likeParams {@link UserDTO#getUserCode() userCode}와
     * {@link Integer boardCode}의 정보가 담겨있습니다.
     * @return 등록 성공 시 1을 반환, 최종적으로 1의 값을 가집니다.
     */
    public int insertLikeChange(Map<String, Integer> likeParams) {
        log.info("[CommunityService] insertLikeChange 호출");
        int result = communityMapper.insertLikeChange(likeParams);
        log.info("[CommunityService] insertLikeChange 종료");
        return result;
    }

    /**
     * 좋아요를 삭제 상태로 변환합니다.
     *
     * @param likeParams {@link UserDTO#getUserCode() userCode}와
     * {@link Integer boardCode}의 정보가 담겨있습니다.
     * @return 등록 성공 시 1을 반환, 최종적으로 -1의 값을 가집니다.
     */
    public int deleteLikeChange(Map<String, Integer> likeParams) {
        log.info("[CommunityService] deleteLikeChange 호출");
        int result = communityMapper.deleteLikeChange(likeParams);
        log.info("[CommunityService] deleteLikeChange 종료");
        return result;
    }

    /**
     * 게시글을 삭제합니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 삭제 성공 시 1을 반환합니다.
     */
    public int deleteBoard(int boardCode) {
        log.info("[CommunityService] deleteBoard 호출");
        int result = communityMapper.deleteBoard(boardCode);
        log.info("[CommunityService] deleteBoard 종료");
        return result;
    }

    /**
     * 저장된 게시글의 사진 파일을 삭제합니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 삭제에 성공한 사진 개수를 반환합니다.
     */
    public int deleteBoardImage(int boardCode) {
        log.info("[CommunityService] deleteBoardImage 호출");
        String direction = IMAGE_DIR_PREFIX + IMAGE_TYPE;
        int result = 0;
        List<ImageTableDTO> files = communityMapper.getFileItems(boardCode);
        if(files == null || files.size() == 0) {
            log.info("[CommunityService] 조회된 이미지가 없습니다!");
            return 0;
        }
        for(ImageTableDTO image : files) {
            log.info("[CommunityService] 파일 삭제를 진행합니다.");
            result += ImageUtils.deleteImage(direction, image);
        }
        log.info("[CommunityService] deleteBoardImage 종료");
        return 0;
    }

    /**
     * 저장된 게시글의 사진 테이블 정보를 삭제합니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 삭제에 성공한 데이터 수를 반환합니다.
     */
    public int deleteBoardImageTable(int boardCode) {
        log.info("[CommunityService] deleteBoardImageTable 호출");
        int result = communityMapper.deleteBoardTable(boardCode);
        log.info("[CommunityService] deleteBoardImageTable 종료");
        return result;
    }

    /**
     * 삭제되는 게시글의 좋아요 정보를 모두 지웁니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 삭제된 수량을 반환합니다.
     */
    public int deleteAllLikeChange(int boardCode) {
        log.info("[CommunityService] deleteBoardImageTable 호출");
        int result = communityMapper.deleteAllLikeChange(boardCode);
        log.info("[CommunityService] deleteBoardImageTable 종료");
        return result;
    }

    /**
     * 게시글 조건에 맞는 모든 댓글들을 불러옵니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 조회된 {@link List}를 반환합니다.
     */
    public List<CommentDTO> selectAllComments(int boardCode) {
        log.info("[CommunityService] selectAllComments 호출");
        List<CommentDTO> result = communityMapper.selectAllComments(boardCode);
        log.info("[CommunityService] selectAllComments 종료");
        return result;
    }

    /**
     * 댓글을 작성합니다.
     *
     * @param commentParams {@code boardCode}, {@code userCode}, {@code ref}
     * 값이 담겨있습니다.
     * @return 성공 시 1을 반환합니다.
     */
    public int registComment(Map<String, String> commentParams) {
        log.info("[CommunityService] selectAllComments 호출");
        int result = communityMapper.registComment(commentParams);
        log.info("[CommunityService] selectAllComments 종료");
        return result;
    }
}
