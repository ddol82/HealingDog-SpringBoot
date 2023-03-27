package com.healing.healingdog.community.model.dto;

import com.healing.healingdog.common.paging.PageData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardDTO {
    private int boardCode;
    private int userCode;
    private int boardCategoryCode;
    private String boardCategoryName;
    private String title;
    private String content;
    private Timestamp uptime;
    private long view;
    private int share;
    private PageData pageData;
}
