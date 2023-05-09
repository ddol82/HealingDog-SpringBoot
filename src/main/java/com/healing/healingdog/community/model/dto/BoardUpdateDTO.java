package com.healing.healingdog.community.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardUpdateDTO {
    private String id;
    private int boardCode;
    private int userCode;
    private int boardCategoryCode;
    private String title;
    private String content;
    private int beforeContains;
    private List<Integer> position;
    private List<Integer> size;
}
