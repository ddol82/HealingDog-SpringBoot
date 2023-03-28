package com.healing.healingdog.common.file.model.dto;

/**
 * images Table에 접근하여 자료를 조회하는 DTO입니다.<br>
 * usages 추적을 위해 {@code @lombok}({@link lombok.AllArgsConstructor @Constructor}, {@link lombok.Getter @Getter},
 * {@link lombok.Setter @Setter})을 사용하지 않고 직접 구현이 되어있습니다.
 *
 * @author 이진녕
 * @since 1.0
 * @version 1.0
 */
public class ImageTableDTO {
    private int code;
    private String imageType;
    private String category;
    private int refCode;
    private String usage;
    private String thumbnail;
    private String original;

    public ImageTableDTO(int code, ImageType imageType, String category, int refCode, String usage, String thumbnail, String original) {
        this.code = code;
        this.imageType = imageType.getType();
        this.category = category;
        this.refCode = refCode;
        this.usage = usage;
        this.thumbnail = thumbnail;
        this.original = original;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType.getType();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRefCode() {
        return refCode;
    }

    public void setRefCode(int refCode) {
        this.refCode = refCode;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
