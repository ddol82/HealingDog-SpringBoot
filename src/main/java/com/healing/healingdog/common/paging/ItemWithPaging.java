package com.healing.healingdog.common.paging;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemWithPaging {

    private PageData pageInfo;
    private Object item;
}
