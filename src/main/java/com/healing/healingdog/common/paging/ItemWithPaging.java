package com.healing.healingdog.common.paging;

import lombok.*;

/**
 * 페이지네이션을 거친 데이터에 추가로<br>페이지 정보를 덧붙여 반환하는 용도의 DTO입니다.
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
public class ItemWithPaging {
    private PageData pageInfo;
    private Object item;
}
