package com.healing.healingdog.community.model.dto;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO {
    private int commentCode;
    private int boardCode;
    private int userCode;
    private int ref;
    private Timestamp uptime;
    private String content;
}
