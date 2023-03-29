package com.healing.healingdog.community.model.dao;

import com.healing.healingdog.common.file.model.dto.ImageTableDTO;
import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.community.model.dto.BoardCreateDTO;
import com.healing.healingdog.community.model.dto.BoardTableDTO;
import com.healing.healingdog.community.model.dto.CatAndPageDataForBoard;
import com.healing.healingdog.community.model.type.BoardType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 커뮤니티의 매퍼 인터페이스.
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

    /**
     * 게시글을 등록합니다.<br>
     * insert된 게시글의 id(PK)값을 반환할 것이며,<br>
     * return 타입이 정해져있을 경우 기존 insert 반환 값이 담기거나<br>
     * {@link org.apache.ibatis.binding.BindingException BindingException}이
     * 출력되는 문제가 있었습니다.<br>
     * Mapper를 void형으로 지정할 경우 {@link BoardCreateDTO parameter}로 쓰인 객체에<br>
     * keyProperty로 설정한 변수에 Mapping이 됩니다.
     *
     * @param boardCreateDTO 등록할 게시글의 정보입니다.
     * 등록 실패 시 {@code null}을 반환합니다.
     */
    void insertBoard(BoardCreateDTO boardCreateDTO);

    /**
     * 게시글 사진을 등록합니다.<br>
     * insert된 사진의 id(PK)값을 반환할 것입니다.
     * @param imageTableDTO
     */
    void insertBoardImage(ImageTableDTO imageTableDTO);
}
