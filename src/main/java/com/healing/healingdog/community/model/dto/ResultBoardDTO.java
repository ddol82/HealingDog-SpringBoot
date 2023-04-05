package com.healing.healingdog.community.model.dto;

import com.healing.healingdog.community.model.type.BoardType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 게시글 리스트 출력에 필요한 정보가 들어있습니다.<br>
 * 게시글의 상세 정보가 필요한 경우, (DTO 링크)를 사용합니다.
 *
 * @author 이진녕
 * @see BoardTableDTO
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResultBoardDTO {
    private int boardCode;
    private int userCode;
    private int boardCategoryCode;
    private String boardCategoryName;
    private String title;
    private String content;
    private String profileImageUrl;
    private String profileName;
    private Timestamp uptimestamp;
    private String uptime;
    private int view;
    private int share;
    private int like;
    private String thumbnailImageUrl;
    private int imageCount;
    private int commentCount;

    /**
     * 테이블의 정보만을 가지고 있는 {@link BoardTableDTO}를 사용해
     * 테이블과 관련된 정보를 채울 수 있습니다.
     * 다음의 내용은 포함되지 않습니다.
     * <ui>
     *     <li>{@link ResultBoardDTO#userCode}</li>
     *     <li>{@link ResultBoardDTO#profileImageUrl}</li>
     *     <li>{@link ResultBoardDTO#profileName}</li>
     *     <li>{@link ResultBoardDTO#thumbnailImageUrl}</li>
     *     <li>{@link ResultBoardDTO#imageCount}</li>
     * </ui>
     *
     * @param boardTableItem {@link BoardTableDTO}타입의 정보입니다.
     */
    public void setBoard(BoardTableDTO boardTableItem) {
        this.boardCode = boardTableItem.getBoardCode();
        this.boardCategoryCode = boardTableItem.getBoardCategoryCode();
        this.boardCategoryName = BoardType.getBoardType(this.boardCategoryCode).getName();
        this.title = boardTableItem.getTitle();
        this.content = boardTableItem.getContent();
        this.uptimestamp = boardTableItem.getUptime();
        this.uptime = new SimpleDateFormat("yy.MM.dd").format(this.uptimestamp);
        this.view = boardTableItem.getView();
        this.share = boardTableItem.getShare();
        this.like = boardTableItem.getLike();
        this.commentCount = boardTableItem.getCommentCount();
    }
}
