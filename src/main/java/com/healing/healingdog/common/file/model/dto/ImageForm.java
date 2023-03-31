package com.healing.healingdog.common.file.model.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * {@code image}를 <b>POST</b>방식의 Form-data로 받기 위한 DTO입니다.<br>
 * usages 추적을 위해 {@link lombok.Lombok @lombok}을 사용하지 않았습니다.
 * 한 장의 경우 {@link ImageForm}로 사용하고,<br>
 * 여러 장의 경우 {@link java.util.List List}<{@link ImageForm}> 형태로 사용합니다.<br>
 * form-data의 형식을 맞춰주세요.<br>
 * <ul>
 *     <li>usage : 구분을 위한 1글자의 {@link String}입니다.</li>
 *     <li>imageFile : {@link MultipartFile}타입의 이미지 파일입니다.</li>
 *     <li>hasThumbnail : {@link String},
 *     썸네일 포함 시 "O", 미포함 시 "X"입니다.</li>
 * </ul>
 * <hr>
 * <table border="1">
 *     <tr>
 *         <td rowspan="4">{@link ImageForm 단일} 사용 - </td>
 *         <td>Key</td>
 *         <td>Value</td>
 *     </tr>
 *     <tr>
 *         <td>ImageForm이름.usage</td>
 *         <td>1</td>
 *     </tr>
 *     <tr>
 *         <td>ImageForm이름.imageFile</td>
 *         <td>[첨부 파일]</td>
 *     </tr>
 *     <tr>
 *         <td>ImageForm이름.hasThumbnail</td>
 *         <td>O</td>
 *     </tr>
 * </table>
 * <hr>
 * <table>
 *     <tr>
 *         <td rowspan="7">{@link java.util.List List} 사용 - </td>
 *         <td>Key</td>
 *         <td>Value</td>
 *     </tr>
 *     <tr>
 *         <td>ImageForm이름[0].usage</td>
 *         <td>P</td>
 *     </tr>
 *     <tr>
 *         <td>ImageForm이름[0].imageFile</td>
 *         <td>[첨부 파일]</td>
 *     </tr>
 *     <tr>
 *         <td>ImageForm이름[0].hasThumbnail</td>
 *         <td>O</td>
 *     </tr>
 *     <tr>
 *         <td>ImageForm이름[1].usage</td>
 *         <td>B</td>
 *     </tr>
 *     <tr>
 *         <td>ImageForm이름[1].imageFile</td>
 *         <td>[첨부 파일]</td>
 *     </tr>
 *     <tr>
 *         <td>ImageForm이름[1].hasThumbnail</td>
 *         <td>X</td>
 *     </tr>
 * </table>
 * <hr>
 * <br>
 * 다음은 예시입니다.
 * <pre>
 * &#064;lombok
 * {@code public class 이미지_쓰는_DTO { //예시 코드
 *     private String content;
 *     ...
 *     private List<ImageForm> files;
 * }}
 * </pre>
 * <table>
 *     <tr>
 *         <td rowspan="9">[ form-data ]</td>
 *         <td>Key</td>
 *         <td>Value</td>
 *     </tr>
 *     <tr>
 *         <td>content</td>
 *         <td>test</td>
 *     </tr>
 *     <tr>
 *         <td colspan="2">...</td>
 *     </tr>
 *     <tr>
 *         <td>이름_작성_예정[0].usage</td>
 *         <td>P</td>
 *     </tr>
 *     <tr>
 *         <td>이름_작성_예정[0].imageFile</td>
 *         <td>[첨부 파일]</td>
 *     </tr>
 *     <tr>
 *         <td>이름_작성_예정[0].hasThumbnail</td>
 *         <td>O</td>
 *     </tr>
 *     <tr>
 *         <td>이름_작성_예정[1].usage</td>
 *         <td>B</td>
 *     </tr>
 *     <tr>
 *         <td>이름_작성_예정[1].imageFile</td>
 *         <td>[첨부 파일]</td>
 *     </tr>
 *     <tr>
 *         <td>이름_작성_예정[1].hasThumbnail</td>
 *         <td>X</td>
 *     </tr>
 * </table>
 *
 * @author 이진녕
 * @since 1.0
 * @version 1.0
 */
public class ImageForm {
    private String usage; //첨부 위치
    private MultipartFile imageFile; // 파일
    private String hasThumbnail; // 썸네일

    public ImageForm(String usage, MultipartFile imageFile, String hasThumbnail) {
        this.usage = usage;
        this.imageFile = imageFile;
        this.hasThumbnail = hasThumbnail;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getHasThumbnail() {
        return hasThumbnail;
    }

    public void setHasThumbnail(String hasThumbnail) {
        this.hasThumbnail = hasThumbnail;
    }

    @Override
    public String toString() {
        return "ImageForm{" +
                "usage='" + usage + '\'' +
                ", imageFile=" + imageFile +
                ", hasThumbnail='" + hasThumbnail + '\'' +
                '}';
    }
}
