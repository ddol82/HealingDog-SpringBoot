package com.healing.healingdog.community.model.dao;

import com.healing.healingdog.common.file.model.dto.ImageTableDTO;
import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.community.model.dto.BoardCreateDTO;
import com.healing.healingdog.community.model.dto.BoardTableDTO;
import com.healing.healingdog.community.model.dto.CommentDTO;
import com.healing.healingdog.community.model.type.BoardType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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
     * @param boardListParams {@link PageData 페이지 정보}, {@link BoardType 카테고리 정보}를 입력받습니다.
     * @return 현재 카테고리에 해당하는 전체 게시글의 수를 반환합니다,<br>
     * 카테고리가 {@link BoardType#ALL 전체 글}일 경우 모든 게시글의 수를 반환합니다.
     */
    List<BoardTableDTO> selectBoardList(Map<String, Integer> boardListParams);

    /**
     * 커뮤니티 상단에 고정되어야 하는 게시글들을 조회합니다.
     * @return 상단 고정 게시글들을 {@link List}<{@link BoardTableDTO}>형태로 반환합니다.
     */
    List<BoardTableDTO> selectBoardHeadline();

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
     * @param imageTableDTO 이미지 정보입니다.
     */
    void insertBoardImage(ImageTableDTO imageTableDTO);

    /**
     * 게시글 번호를 입력받아 상세조회합니다.
     * @param boardCode 게시글 번호입니다.
     * @return 게시글 정보를 반환합니다.
     */
    BoardTableDTO selectBoardDetail(int boardCode);

    /**
     * 게시글의 좋아요 수를 조회합니다.
     *
     * @param boardCode 대상 게시글입니다.
     * @return 좋아요 수를 반환합니다.
     */
    int selectAllLikeActivityDetail(int boardCode);

    /**
     * 게시글의 공유 수를 조회합니다.
     *
     * @param boardCode 대상 게시글입니다.
     * @return 공유 수를 반환합니다.
     */
    int selectAllShareActivityDetail(int boardCode);

    /**
     * 게시글의 댓글 수를 조회합니다.
     *
     * @param boardCode 대상 게시글입니다.
     * @return 댓글 수를 반환합니다.
     */
    int selectAllCommentActivityDetail(int boardCode);

    /**
     * 게시글 번호를 입력받아 모든 이미지 URL을 조회합니다.
     * @param boardCode 게시글 번호입니다.
     * @return URL 정보를 {@link List}<{@link String}> 타입으로 반환합니다.
     */
    List<String> selectBoardOriginalUrl(int boardCode);

    /**
     * 대상 게시글의 썸네일 경로를 조회합니다.
     *
     * @param boardCode 대상 게시글의 PK입니다.
     * @return 썸네일의 경로가 {@link String}타입으로 반환됩니다.
     */
    String selectBoardThumbnailUrl(int boardCode);

    /**
     * 게시글 번호를 입력받아 모든 미리보기 URL을 조회합니다.
     * @param boardCode 게시글 번호입니다.
     * @return URL 정보를 {@link List}<{@link String}> 타입으로 반환합니다.
     */
    List<String> selectBoardPreviewUrl(int boardCode);

    /**
     * 게시글 좋아요 상태를 확인합니다.
     *
     * @param userCode 현재 로그인한 유저 코드 및 게시글 코드입니다.
     * @return 기본 0, 좋아요가 이미 되어있을 시 1이 반환됩니다.
     */
    int checkLikeState(Map<String, Integer> userCode);

    /**
     * 게시글의 조회 수를 1 올립니다.
     *
     * @param boardCode 대상 게시글입니다.
     * @return 성공 시 1이 반환됩니다.
     */
    int viewIncrement(int boardCode);

    /**
     * 게시글의 공유 수를 1 올립니다.
     *
     * @param boardCode 대상 게시글입니다.
     * @return 성공 시 1이 반환됩니다.
     */
    int shareIncrement(int boardCode);

    /**
     * 게시글의 좋아요를 등록합니다.
     *
     * @param likeParams 현재 유저 코드와 대상 게시글 코드입니다.
     * @return 성공 시 1이 반환됩니다.
     */
    int insertLikeChange(Map<String, Integer> likeParams);

    /**
     * 게시글의 좋아요를 삭제합니다.
     *
     * @param likeParams 현재 유저 코드와 대상 게시글 코드입니다.
     * @return 성공 시 1이 반환됩니다.
     * {@link com.healing.healingdog.community.controller.CommunityController controller}
     * 에서 최종적으로 -1값을 가집니다.
     */
    int deleteLikeChange(Map<String, Integer> likeParams);

    /**
     * 게시글을 삭제합니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 삭제 성공 시 1을 반환합니다.
     */
    int deleteBoard(int boardCode);

    /**
     * 삭제할 이미지 정보들을 가져옵니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 이미지 리스트를 가져옵니다.
     */
    List<ImageTableDTO> getFileItems(int boardCode);

    /**
     * 삭제할 이미지 파일의 DB 정보들을 가져옵니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 삭제에 성공한 DB 개수를 반환합니다.
     */
    int deleteBoardTable(int boardCode);

    /**
     * 삭제되는 게시글의 좋아요 정보를 모두 지웁니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 삭제된 수량을 반환합니다.
     */
    int deleteAllLikeChange(int boardCode);

    /**
     * 게시글의 사진 일부를 삭제합니다.
     *
     * @param unusedParams 대상 게시글 코드와 삭제 대상입니다.
     * @return 삭제된 수량을 반환합니다.
     */
    int deleteBoardTableWithUsage(Map<String, String> unusedParams);

    /**
     * 게시글 조건에 맞는 댓글들을 모두 조회합니다.
     *
     * @param boardCode 대상 게시글 코드입니다.
     * @return 댓글 정보가 담긴 {@link CommentDTO}들을 반환합니다.
     */
    List<CommentDTO> selectAllComments(int boardCode);

    /**
     * 댓글을 작성합니다.
     *
     * @param commentParams {@code boardCode}, {@code userCode}, {@code ref}
     * 값이 담겨있습니다.
     * @return 성공 시 1을 반환합니다.
     */
    int registComment(Map<String, String> commentParams);

    /**
     * 게시글의 사진 용량 정보를 불러옵니다.
     *
     * @param boardCode 대상 게시글의 코드입니다.
     * @return 사진 용량 정보를 반환합니다.
     */
    List<Integer> selectBoardSizeCount(int boardCode);

    /**
     * 사진의 위치를 변경합니다.
     *
     * @param moveParams 변경 전 정보와 변경 후 정보가 담겨있습니다.
     * @return 성공 시 1을 반환합니다.
     */
    int updateBoardImageUsage(Map<String, String> moveParams);

    /**
     * 게시글을 수정합니다.
     *
     * @param boardUpdate 대상 게시글 코드입니다.
     */
    void updateBoard(BoardCreateDTO boardUpdate);
}
