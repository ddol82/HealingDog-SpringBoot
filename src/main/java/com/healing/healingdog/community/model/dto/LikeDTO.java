package com.healing.healingdog.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class LikeDTO {
    private long likeCode;
    private int boardCode;
    private int userCode;
    private Timestamp time;
}
