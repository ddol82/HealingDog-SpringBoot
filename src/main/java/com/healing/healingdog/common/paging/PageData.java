package com.healing.healingdog.common.paging;

import lombok.*;

/**
 * 페이지 정보가 담긴 DTO Class입니다.
 * {@link ItemWithPaging} 클래스에 데이터와 함께 포함되어 return되는 용도입니다.
 * {@link PageData#currPage 현재_페이지}와 {@link PageData#itemAllCount 모든_항목_수} 정보를 가지고 있다면<br>
 * {@link PageDataAutoFill} 클래스를 통해 나머지 정보를 자동으로 채울 수 있습니다.
 *
 * @author 이진녕
 * @version 1.0
 * @since 1.0
 */
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
