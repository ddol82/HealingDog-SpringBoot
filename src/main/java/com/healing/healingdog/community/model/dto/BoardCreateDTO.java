package com.healing.healingdog.community.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@ToString
public class BoardCreateDTO {
    private int userCode;
    private int boardCategoryCode;
    private String title;
    private String content;
    private Timestamp uptime;
    private List<MultipartFile> files;
}
