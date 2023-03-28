package com.healing.healingdog.community.model.dto;

import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.community.model.type.BoardType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * boards 테이블 조회 시 필요한 파라미터가 담긴 DTO입니다.<br>
 * {@link BoardType Category}의 {@link BoardType#getCode() code}와
 * {@link PageData Page}의 커서 정보가 담겨있습니다.
 *
 * @author 이진녕
 * @see BoardType
 * @see PageData
 * @since 1.0
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class CatAndPageDataForBoard {
    private int start;
    private int end;
    private int categoryCode;
    public CatAndPageDataForBoard(PageData page, BoardType boardType) {
        this.start = page.getStartCursor();
        this.end = page.getEndCursor();
        this.categoryCode = boardType.getCode();
    }
}
