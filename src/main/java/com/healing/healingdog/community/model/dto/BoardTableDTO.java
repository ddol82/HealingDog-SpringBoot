package com.healing.healingdog.community.model.dto;

import lombok.*;

import java.sql.Timestamp;

/**
 * boards Table의 정보를 담기 위한 DTO입니다.
 *
 *
 * @author 이진녕
 * @see SimpleBoardDTO
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardTableDTO {
    private int boardCode;
    private int userCode;
    private int boardCategoryCode;
    private String title;
    private String content;
    private Timestamp uptime;
    private int view;
    private int share;
    private int like;
}
