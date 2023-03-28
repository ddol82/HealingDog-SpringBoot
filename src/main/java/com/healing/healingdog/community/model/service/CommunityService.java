package com.healing.healingdog.community.model.service;

import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.community.model.dao.CommunityMapper;
import com.healing.healingdog.community.model.dto.BoardTableDTO;
import com.healing.healingdog.community.model.dto.CatAndPageDataForBoard;
import com.healing.healingdog.community.model.type.BoardType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final CommunityMapper communityMapper;

    public CommunityService(CommunityMapper communityMapper) {
        this.communityMapper = communityMapper;
    }

    /**
     * 커뮤니티 상단에 고정되어야 하는 게시글을 조회합니다.
     * @return 조회된 게시글을 {@link List}형태로 반환합니다.
     */
    public List<BoardTableDTO> selectBoardHeadline() {
        log.info("[CommunityService] selectBoardHeadline 호출");
        List<BoardTableDTO> boardList= communityMapper.selectBoardHeadline();
        log.debug("(selectBoardCount) 조회 결과 : " + boardList.size() + "건");
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
        return count;
    }
}
