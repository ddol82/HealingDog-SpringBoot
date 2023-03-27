package com.healing.healingdog.community.model.dao;

import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.community.model.dto.BoardDTO;
import com.healing.healingdog.community.model.dto.DetailForGettingBoard;
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
     * @param detailData {@link DetailForGettingBoard}<br>({@link PageData 페이지 정보} + {@link BoardType 카테고리 정보})를 입력받습니다.
     * @return 현재 카테고리에 해당하는 전체 게시글의 수를 반환합니다,<br>
     * 카테고리가 {@link BoardType#ALL 전체 글}일 경우 모든 게시글의 수를 반환합니다.
     */
    List<BoardDTO> selectBoardList(DetailForGettingBoard detailData);

    /**
     * 커뮤니티 상단에 고정되어야 하는 게시글들을 조회합니다.
     * @return 상단 고정 게시글들을 {@link List}<{@link BoardDTO}>형태로 반환합니다.
     */
    List<BoardDTO> selectBoardHeadline();
}
