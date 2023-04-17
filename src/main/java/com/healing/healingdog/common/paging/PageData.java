package com.healing.healingdog.common.paging;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PageData {
    private int itemAllCount;
    private int pageAmount;
    private int currPage;
    private int startPage;
    private int endPage;
    private int startCursor;
    private int endCursor;
}
