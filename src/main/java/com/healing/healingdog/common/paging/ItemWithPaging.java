package com.healing.healingdog.common.paging;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemWithPaging {

    private PageData pageData;
    private Object data;
}
