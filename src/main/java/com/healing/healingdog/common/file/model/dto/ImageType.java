package com.healing.healingdog.common.file.model.dto;

/**
 * 사진의 용도를 묶어놓은 enum입니다.
 *
 * @author 이진녕
 * @version 1.0
 * @since 1.0
 */
public enum ImageType {
    BOARD("board_images"),
    CERTIFICATE("certificate_images"),
    MEMBER("member_images"),
    REVIEW("review_images"),
    SERVICE("service_images");

    private final String type;
    ImageType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
