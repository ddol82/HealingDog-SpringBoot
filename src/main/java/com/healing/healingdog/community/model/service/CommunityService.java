package com.healing.healingdog.community.model.service;

import com.healing.healingdog.common.file.model.dto.ImageForm;
import com.healing.healingdog.common.file.model.dto.ImageTableDTO;
import com.healing.healingdog.common.file.model.dto.ImageType;
import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.common.util.ImageUtils;
import com.healing.healingdog.community.model.dao.CommunityMapper;
import com.healing.healingdog.community.model.dto.BoardCreateDTO;
import com.healing.healingdog.community.model.dto.BoardTableDTO;
import com.healing.healingdog.community.model.dto.CatAndPageDataForBoard;
import com.healing.healingdog.community.model.type.BoardType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public List<String> selectCommunityCategoryList() {
        List<String> categoryList = new ArrayList<>();
        for(BoardType boardType : BoardType.values()) {
            categoryList.add(boardType.getName());
        }
        return categoryList;
    }

    /**
     * 커뮤니티 상단에 고정되어야 하는 게시글을 조회합니다.
     * @return 조회된 게시글을 {@link List}형태로 반환합니다.
     */
    public List<BoardTableDTO> selectBoardHeadline() {
        log.info("[CommunityService] selectBoardHeadline 호출");

        List<BoardTableDTO> boardList= communityMapper.selectBoardHeadline();
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
     * 대상 게시글의 썸네일 경로를 조회합니다.
     *
     * @param boardCode 대상 게시글의 PK입니다.
     * @return 썸네일의 경로가 {@link String}타입으로 반환됩니다.
     */
    public String selectBoardThumbnailUrl(int boardCode) {
        log.info("[CommunityService] selectBoardThumbnailUrl 호출");

        String thumbnailUrl = communityMapper.selectBoardThumbnailUrl(boardCode);
        log.debug("(selectBoardThumbnailUrl) 조회 결과 : " + (thumbnailUrl != null ? thumbnailUrl : "없음 - null 반환"));

        log.info("[CommunityService] selectBoardThumbnailUrl 종료");
        return thumbnailUrl;
    }

    /**
     * 대상 게시글의 이미지 개수를 조회합니다.
     *
     * @param boardCode 대상 게시글의 PK입니다.
     * @return 게시글 내 모든 이미지 수를 반환됩니다.
     */
    public int selectBoardImageCount(int boardCode) {
        log.info("[CommunityService] selectBoardImageCount 호출");

        int count = communityMapper.selectBoardImageCount(boardCode);
        log.debug("(selectBoardImageCount) 조회 결과 : " + count + "건");

        log.info("[CommunityService] selectBoardImageCount 종료");
        return count;
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
        List<ImageForm> files = boardCreateDTO.getFileItems();
        for(ImageForm imageFile : files) {
            try {
                log.info("[CommunityService] 이미지 1건의 저장을 시도합니다.");
                String original = ImageUtils.saveImage(direction, imageFile.getImageFile());
                String thumbnail = null;
                if(imageFile.getHasThumbnail().equals("O")) {
                    log.info("[CommunityService] 썸네일을 포함해서요!");
                    int resizeWidth = 100;
                    int resizeHeight = 100;
                    thumbnail = ImageUtils.saveThumbnail(direction, imageFile.getImageFile(), resizeWidth, resizeHeight);
                }

                ImageTableDTO imageTableDTO = new ImageTableDTO();
                imageTableDTO.setImageType(ImageType.BOARD);
                imageTableDTO.setRefCode(boardCreateDTO.getId());
                imageTableDTO.setUsage(imageFile.getUsage());
                imageTableDTO.setOriginal(original);
                imageTableDTO.setThumbnail(thumbnail);
                communityMapper.insertBoardImage(imageTableDTO);

                result.add("original - " + original);
                if(thumbnail != null) {
                    result.add("thumbnail - " + thumbnail);
                }
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
        List<String> result = new ArrayList<>();

        if(resultBoard == null) {
            result.add("(fail) 게시글을 등록할 수 없었습니다.");
            return result;
        }
        result.add(resultBoard);
        result.addAll(resultImage);

        return result;
    }
}
