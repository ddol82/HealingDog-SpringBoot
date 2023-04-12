package com.healing.healingdog.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategotyDTO {
    private int code;
    private String type;
    private String name;
}
