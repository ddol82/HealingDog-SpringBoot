package com.healing.healingdog.community.model.dto;

import com.healing.healingdog.common.paging.PageData;
import com.healing.healingdog.community.model.type.BoardType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DetailForGettingBoard {
    private int start;
    private int end;
    private int categoryCode;
    public DetailForGettingBoard(PageData page, BoardType boardType) {
        this.start = page.getStartCursor();
        this.end = page.getEndCursor();
        this.categoryCode = boardType.getCode();
    }
}
