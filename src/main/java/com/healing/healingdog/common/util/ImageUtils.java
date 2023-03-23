package com.healing.healingdog.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 이미지를 관리합니다.
 * 파일 이름은 UUID를 이용하여 랜덤 생성합니다.
 * {@link Slf4j} Annotation을 사용하여 log를 통한 Logger사용이 가능합니다.
 * <pre>
 * {@code
 * ImageUtils imageUtils = new ImageUtils();
 * imageUtils.uploadImage(...);
 * }
 * </pre>
 * @author 이진녕
 * @version 1.0
 */
@Slf4j
public class ImageUtils {
    @Value("${image.image-directory-prefix}")
    private String IMAGE_DIRECTION_PREFIX;

    /**
     * 이미지 파일 한 개를 업로드합니다.
     *
     * @param imageType 업로드 할 이미지의 타입이 입력됩니다.<br>(입력 가능한 값 : member/, service/, review/, board/, certificate/)
     * @param targetFile 업로드 할 {@link MultipartFile}입니다.
     * @return 업로드에 성공한 파일의 이름을 {@link List}<{@link String}> 형태로 출력합니다.
     * @throws IOException 파일 업로드 실패 시 {@link IOException}이 발생합니다.
     */
    public List<String> uploadImage(String imageType, MultipartFile targetFile) throws IOException {
        List<MultipartFile> targetFileList = new ArrayList<>();
        targetFileList.add(targetFile);
        return makeFile(imageType, targetFileList);
    }
    /**
     * 이미지 파일을 {@link List}<{@link MultipartFile}> 형태로 여러 개를 업로드합니다.
     *
     * @param imageType 업로드 할 이미지의 타입이 입력됩니다.<br>(입력 가능한 값 : member/, service/, review/, board/, certificate/)
     * @param targetFileList 업로드 할 {@link MultipartFile}의 {@link List}입니다.
     * @return 업로드에 성공한 파일의 이름을 {@link List}<{@link String}> 형태로 출력합니다.
     * @throws IOException 파일 업로드 실패 시 {@link IOException}이 발생합니다.
     */
    public List<String> uploadImage(String imageType, List<MultipartFile> targetFileList) {
        return makeFile(imageType, targetFileList);
    }

    private List<String> makeFile(String imageType, List<MultipartFile> multipartFileList) {
//        Path uploadPath = Paths.get(IMAGE_DIRECTION_PREFIX + imageType + "/");
//        try {
//            if(!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath);
//            }
//        } catch (IOException e) {
//            log.error("경로를 생성할 수 없었습니다 : " + uploadPath, e);
//            throw new IOException(e);
//        }
//
//        int result = 0;
//        for(MultipartFile targetFile : targetFileList) {
//            if(copyNameChangedImage(uploadPath, targetFile)) {
//                result += 1;
//            }
//        }
        return null;
    }
    private String createRandomImageName(MultipartFile multipartFile) {
        String randomFileName = UUID.randomUUID().toString().replace("-", "");
        return randomFileName + "." + FilenameUtils.getExtension(multipartFile.getResource().getFilename());
    }

    private boolean copyNameChangedImage(Path uploadPath, MultipartFile targetFile) {
        String fileName = createRandomImageName(targetFile);
        try(InputStream inputStream = targetFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("파일을 저장하지 못했습니다 : " + fileName, e);
            return false;
        }
        return true;
    }

    /**
     * 이미지를 삭제합니다.
     *
     * @param uploadDir 이미지의 경로가 입력됩니다.<br>(입력 가능한 값 : member/, service/, review/, board/, certificate/)
     * @param fileName 업로드 할 파일의 이름입니다.
     * @return 삭제 성공 시 True가 반환됩니다. 삭제 중 오류가 발생할 시 False가 반환됩니다.
     */
    public boolean deleteImage(String uploadDir, String fileName) {
        Path imagePath = Paths.get(IMAGE_DIRECTION_PREFIX + uploadDir);
        if(!Files.exists(imagePath)) {
            log.warn("해당 경로가 존재하지 않습니다. : " + fileName);
            return false;
        }

        try {
            Path filePath = imagePath.resolve(fileName);
            Files.delete(filePath);
        } catch (IOException e) {
            log.warn("파일을 삭제할 수 없었습니다. : " + fileName, e);
            return false;
        }

        return true;
    }
}
