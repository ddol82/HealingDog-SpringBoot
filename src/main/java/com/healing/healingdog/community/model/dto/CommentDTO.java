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
    private String userName;
    private int ref;
    private Timestamp uptime;
    private String content;
    //boolean만 is prefix가 붙어 인식이 잘 안 됩니다. Wrapper type으로 대체!
    private Boolean isMine;
}
