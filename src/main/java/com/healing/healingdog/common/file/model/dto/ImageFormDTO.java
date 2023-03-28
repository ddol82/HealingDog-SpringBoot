package com.healing.healingdog.common.file.model.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * {@code image}를 Form-data로 받기 위한 DTO입니다.<br>
 * 한 장의 경우 {@link ImageFormDTO}로 사용하고,<br>
 * 여러 장의 경우 {@link java.util.List List}<{@link ImageFormDTO}> 형태로 사용합니다.
 * form-data는 다음의 형식을 맞춰주세요.
 * <table border="1">
 *     <tr>
 *         <td rowspan="4">단일 사용 - </td>
 *         <td>Key</td>
 *         <td>Value</td>
 *     </tr>
 *     <tr>
 *         <td>ImageFormDTO.id</td>
 *         <td>1</td>
 *     </tr>
 *     <tr>
 *         <td>ImageFormDTO.original</td>
 *         <td>[첨부 파일]</td>
 *     </tr>
 *     <tr>
 *         <td>ImageFormDTO.thumbnail</td>
 *         <td>[첨부 파일]</td>
 *     </tr>
 *     <tr>
 *         <td rowspan="4">{@link java.util.List List} 사용 - </td>
 *         <td>Key</td>
 *         <td>Value</td>
 *     </tr>
 *     <tr>
 *         <td>ImageFormDTO[0].id</td>
 *         <td>1</td>
 *     </tr>
 *     <tr>
 *         <td>ImageFormDTO[0].original</td>
 *         <td>[첨부 파일]</td>
 *     </tr>
 *     <tr>
 *         <td>ImageFormDTO[0].thumbnail</td>
 *         <td>[첨부 파일]</td>
 *     </tr>
 * </table>
 *
 * @author 이진녕
 * @since 1.0
 * @version 1.0
 */
public class ImageFormDTO {
    private String id;
    private MultipartFile original;
    private MultipartFile thumbnail;
}
