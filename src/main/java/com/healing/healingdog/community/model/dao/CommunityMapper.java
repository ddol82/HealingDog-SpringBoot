package com.healing.healingdog.community.model.dao;

import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.community.model.dto.BoardTableDTO;
import com.healing.healingdog.community.model.dto.CatAndPageDataForBoard;
import com.healing.healingdog.community.model.type.BoardType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 커뮤니티의 매퍼 인터페이스입니다.
 * @since 1.0
 * @version 1.0
 * @author 이진녕
 */
@Mapper
public interface CommunityMapper {
    /**
     * 현재 카테고리에 해당하는 전체 게시글의 수를 조회합니다.
     * @param categoryCode {@link BoardType#getCode() 카테고리의 코드}입니다.
     * @return 현재 카테고리에 해당하는 전체 게시글의 수를 반환합니다,<br>
     * 카테고리가 {@link BoardType#ALL 전체 글}일 경우 모든 게시글의 수를 반환합니다.
     */
    int selectBoardCountAll(int categoryCode);

    /**
     * 현재 카테고리에 해당하는 전체 게시글들을 조회합니다.
     * @param detailData {@link CatAndPageDataForBoard}<br>({@link PageData 페이지 정보} + {@link BoardType 카테고리 정보})를 입력받습니다.
     * @return 현재 카테고리에 해당하는 전체 게시글의 수를 반환합니다,<br>
     * 카테고리가 {@link BoardType#ALL 전체 글}일 경우 모든 게시글의 수를 반환합니다.
     */
    List<BoardTableDTO> selectBoardList(CatAndPageDataForBoard detailData);

    /**
     * 커뮤니티 상단에 고정되어야 하는 게시글들을 조회합니다.
     * @return 상단 고정 게시글들을 {@link List}<{@link BoardTableDTO}>형태로 반환합니다.
     */
    List<BoardTableDTO> selectBoardHeadline();

    /**
     * 대상 게시글의 썸네일 경로를 조회합니다.
     *
     * @param boardCode 대상 게시글의 PK입니다.
     * @return 썸네일의 경로가 {@link String}타입으로 반환됩니다.
     */
    String selectBoardThumbnailUrl(int boardCode);

    /**
     * 대상 게시글의 이미지 개수를 조회합니다.
     *
     * @param boardCode 대상 게시글의 PK입니다.
     * @return 게시글 내 모든 이미지 수를 반환됩니다.
     */
    int selectBoardImageCount(int boardCode);
}
