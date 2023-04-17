package com.healing.healingdog.community.model.type;

import lombok.Getter;
import lombok.ToString;

/**
 * 게시판의 카테고리 정보의 열거형입니다.
 *
 * @author 이진녕
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
public enum BoardType {
    NOTICE(1, "notice", "공지사항"),
    EVENT(2, "event", "이벤트"),
    SUGGEST(3, "suggest", "건의사항"),
    REPORT(4, "report", "신고 게시판"),
    ALL(5, "all", "전체 글"),
    HOT(6, "hot", "인기 글"),
    SHARE(7, "share", "정보 공유"),
    QUESTION(8, "question", "질문하기"),
    PLACE(9, "place", "장소 후기"),
    PRODUCT(10, "product", "제품 후기"),
    WELCOME(11, "welcome", "가입인사"),
    ATTENDANCE(12, "attendance", "출석체크"),
    WALKING(13, "walking", "산책 게시판"),
    FREE(14, "free", "자유 게시판");


    private int code;
    private String type;
    private String name;
    BoardType(int code, String type, String name) {
        this.code = code;
        this.type = type;
        this.name = name;
    }

    private static final BoardType[] VALUES;
    static {
        VALUES = values();
    }

    /**
     * 카테고리 코드를 입력받고 해당하는 {@link BoardType} 정보를 얻어옵니다.
     * 해당하는 값이 없는 경우, 기본적으로 <b>전체 게시글</b>을 조회하게 됩니다.
     *
     * @param value 카테고리 코드를 받습니다.
     * @return 해당하는 {@code BoardType}를 반환합니다. 값이 존재하지 않는 경우 default 값 {@link BoardType#ALL}을 반환합니다.
     */
    public static BoardType getBoardType(int value) {
        return getBoardTypeByCode(value);
    }

    /**
     * 카테고리 타입을 입력받고 해당하는 {@link BoardType} 정보를 얻어옵니다.
     * 해당하는 값이 없는 경우, 기본적으로 <b>전체 게시글</b>을 조회하게 됩니다.
     *
     * @param type 카테고리 타입을 받습니다.
     * @return 해당하는 {@code BoardType}를 반환합니다. 값이 존재하지 않는 경우 default 값 {@link BoardType#ALL}을 반환합니다.
     */
    public static BoardType getBoardType(String type) {
        return getBoardTypeByType(type);
    }

    private static BoardType getBoardTypeByCode(int value) {
        for(BoardType board : VALUES) {
            if(board.code == value) {
                return board;
            }
        }
        return BoardType.ALL;
    }

    private static BoardType getBoardTypeByType(String type) {
        for(BoardType board : VALUES) {
            if(board.type.equals(type)) {
                return board;
            }
        }
        return BoardType.ALL;
    }
}
