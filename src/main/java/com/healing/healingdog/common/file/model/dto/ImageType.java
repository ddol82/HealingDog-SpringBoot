package com.healing.healingdog.common.file.model.dto;

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
