package com.healing.healingdog.community.model.service;

import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.community.model.dao.CommunityMapper;
import com.healing.healingdog.community.model.dto.BoardDTO;
import com.healing.healingdog.community.model.dto.DetailForGettingBoard;
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
    public List<BoardDTO> selectBoardHeadline() {
        log.info("[CommunityService] selectBoardHeadline 호출");
        List<BoardDTO> boardList= communityMapper.selectBoardHeadline();
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
     * @param detailData {@link DetailForGettingBoard} 타입을 사용하며, 게시글의 인덱스 정보와 카테고리 코드 정보를 가지고 있습니다.
     * @return 카테고리 타입이 일치하고, {@link PageData#getStartCursor 시작 인덱스}와 {@link PageData#getEndCursor() 끝 인덱스}
     * 사이의 범위에 있는 게시글들을 {@link List}에 담아 반환합니다.
     */
    public List<BoardDTO> selectBoardList(DetailForGettingBoard detailData) {
        log.info("[CommunityService] selectBoardList 호출");
        List<BoardDTO> boardList= communityMapper.selectBoardList(detailData);
        log.debug("(selectBoardCount) 조회 결과 : " + boardList.size() + "건");
        return boardList;
    }
}
