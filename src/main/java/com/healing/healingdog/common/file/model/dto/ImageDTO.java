package com.healing.healingdog.common.file.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ImageDTO {
    private int code;
    private ImageType imageType;
    private String category;
    private int refCode;
    private String usage;
    private String thumbnail;
    private String original;
}
