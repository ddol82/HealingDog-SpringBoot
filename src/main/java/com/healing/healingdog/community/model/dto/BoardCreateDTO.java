package com.healing.healingdog.community.model.dto;

import com.healing.healingdog.common.file.model.dto.ImageFormDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardCreateDTO {
    private String id;
    private int userCode;
    private int boardCategoryCode;
    private String title;
    private String content;
    private List<Integer> size;
    private List<ImageFormDTO> fileItems;
}
