package com.healing.healingdog.common.util;

import com.healing.healingdog.common.file.model.dto.ImageTableDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * 이미지 파일을 관리합니다.
 * 파일 이름은 UUID를 이용하여 랜덤 생성합니다.<br>
 * {@link Slf4j @Slf4j} 가 포함되어있어 {@code log}를 통한 Logger사용이 가능합니다.<br>
 * Singleton Pattern으로 제작되었습니다<br>예)
 * {@code ImageUtils.saveImage(...);}
 * @since 1.0
 * @author 이진녕
 * @version 1.0
 */
@Slf4j
public class ImageUtils {

    /**
     * 파일을 저장합니다.<br>
     * 파일의 이름은 {@link UUID}를 이용하여 랜덤으로 생성됩니다.
     *
     * @param uploadDir 업로드 할 경로입니다.
     * @param multipartFile 업로드 할 이미지 파일입니다.
     * @return 파일 저장 후 저장된 이름의 파일명을 반환합니다.
     * @throws IOException 입출력 과정에서 오류 발생 시 오류를 출력합니다.
     */
    public static String saveImage(String uploadDir, MultipartFile multipartFile) throws IOException {
        log.info("saveFile 호출");
        Path uploadPath = Paths.get(uploadDir);

        pathExistCheckForSave(uploadPath);
        //이름 랜덤 생성
        String saveName = createRandomImageName(multipartFile);
        //경로에 파일 복사
        fileCopy(uploadPath, multipartFile, saveName);

        log.info("saveFile 종료");
        return saveName;
    }

    /**
     * 파일의 썸네일을 저장합니다.<br>
     * 썸네일의 이름은 {@link UUID}를 이용하여 랜덤으로 생성됩니다.
     *
     * @param uploadDir 업로드 할 경로입니다.
     * @param multipartFile 업로드 할 이미지 파일입니다.
     * @return 파일 저장 후 저장된 이름의 파일명을 반환합니다.
     * @throws IOException 입출력 과정에서 오류 발생 시 오류를 출력합니다.
     */
    public static String saveThumbnail(String uploadDir, MultipartFile multipartFile, int width, int height) throws IOException {
        log.info("saveThumbnail 호출");
        Path uploadPath = Paths.get(uploadDir);
        log.debug("경로 : {}", uploadPath.toRealPath());
        //경로 확인
        pathExistCheckForSave(uploadPath);
        //이름 랜덤 생성
        String saveName = createRandomImageName(multipartFile);
        //경로에 파일 복사
        String uploadFullDir = uploadDir + saveName;
        try {
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            //BufferedImage bufferedImage = getFileOrientation(multipartFile);
            bufferedImage = resizeImage(bufferedImage, width, height);
            ImageIO.write(
                    bufferedImage,
                    saveName.split("\\.")[1],
                    new File(uploadFullDir)
            );
        } catch (IOException e) {
            log.error("이미지 resize 과정에 오류가 발생했습니다.", e);
            throw new IOException(e);
        }

        log.info("saveThumbnail 종료");
        return saveName;
    }

    /**
     * 파일의 경로가 존재하는지 확인하여<br>
     * 존재하지 않는 경우 경로를 새로 생성합니다.
     * @param uploadPath 존재를 확인할 경로입니다.
     * @throws IOException 입출력 과정에서 오류 발생 시 IOException이 발생합니다.
     */
    private static void pathExistCheckForSave(Path uploadPath) throws IOException {
        try {
            if(!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                log.info("폴더 생성 : {}", uploadPath);
            }
        } catch (IOException e) {
            log.error("경로를 생성할 수 없었습니다 : " + uploadPath, e);
            throw new IOException(e);
        }
    }

    /**
     * 파일의 이름을 {@link UUID}를 사용한 랜덤 문자열로 만들고<br>
     * 확장자를 붙입니다.
     *
     * @param multipartFile 확장자를 붙일 파일입니다.
     * @return "랜덤_이름.확장자" 이름이 반환됩니다.
     */
    private static String createRandomImageName(MultipartFile multipartFile) {
        String randomFileName = UUID.randomUUID().toString()
                .replace("-", "");
        randomFileName += "." + FilenameUtils.getExtension(multipartFile.getResource().getFilename());
        return randomFileName;
    }

    /**
     * 생성된 이름으로 이미지 파일을 복사합니다.
     *
     * @param uploadPath 복사를 진행할 경로입니다.
     * @param multipartFile 복사할 파일입니다.
     * @param fileName 생성된 파일 이름입니다.
     * @throws IOException 입출력 과정에서 오류 발생 시 IOException이 발생합니다.
     */
    private static void fileCopy(Path uploadPath, MultipartFile multipartFile, String fileName) throws IOException {
        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("파일을 저장하지 못했습니다 : " + fileName, e);
            throw new IOException("파일을 저장할 수 없었습니다 : " + fileName, e);
        }
    }

    /**
     * 파일을 리사이즈합니다.<br>사진이 작을 경우 원본을 반환하고,
     * 사진이 클 경우 지정한 사이즈로 리사이즈합니다.<br>
     * 파일의 원본 비율은 유지됩니다.
     *
     * @param bufferedImage 리사이즈 대상 이미지입니다.
     * @param width 리사이즈가 될 폭입니다.
     * @param height 리사이즈가 될 높이입니다.
     * @return 리사이즈 된 {@link BufferedImage}를 출력합니다.
     */
    private static BufferedImage resizeImage(BufferedImage bufferedImage, int width, int height) {
        int getWidth = bufferedImage.getWidth();
        int getHeight = bufferedImage.getHeight();
        //사이즈 조절이 필요없으면 가공 없이 반환
        if(getWidth < width) width = getWidth;
        if(getHeight < height) height = getHeight;
        if(width == getWidth && height == getHeight) return bufferedImage;

        //비율에 맞춰 사이즈 조절
        double ratio = 1.0;
        ratio = Math.min(ratio, (double) width/getWidth);
        ratio = Math.min(ratio, (double) height/getHeight);

        int resizeWidth = (int) Math.floor((double) getWidth * ratio);
        int resizeHeight = (int) Math.floor((double) getHeight * ratio);

        return Scalr.resize(
                bufferedImage,
                Scalr.Method.AUTOMATIC,
                resizeWidth,
                resizeHeight,
                Scalr.OP_ANTIALIAS
        );
    }

//    /**
//     * 이미지 회전을 막기 위해 내부적으로 사용하는 메소드.
//     *
//     * @param multipartFile 회전 값 정보를 얻기 위한 파일입니다.
//     * @return 파일의 회전 값 정보를 반환합니다.
//     * @throws IOException 파일 입출력 과정에서 오류 발생 시 오류를 반환합니다.
//     */
//    private static BufferedImage getFileOrientation(MultipartFile multipartFile) throws IOException {
//        int orientation = 1;
//        JpegDirectory jpegDirectory;
//        try {
//            Metadata metadata = ImageMetadataReader.readMetadata(multipartFile.getResource());
//            Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
//            jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
//            if(directory != null) {
//                orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
//            }
//        } catch (ImageProcessingException e) {
//            log.warn("이미지 처리 중 문제가 발생했습니다 : ", e);
//        } catch (MetadataException e) {
//            log.warn("orientation 값을 불러올 수 없었습니다 : ", e);
//        }
//
//        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
//        switch (orientation) {
//            case 1: break;
//            case 3: bufferedImage = Scalr.rotate(bufferedImage, Scalr.Rotation.CW_180, null); break;
//            case 6: bufferedImage = Scalr.rotate(bufferedImage, Scalr.Rotation.CW_90, null); break;
//            case 8: bufferedImage = Scalr.rotate(bufferedImage, Scalr.Rotation.CW_270, null); break;
//        }
//
//        return bufferedImage;
//    }

    /**
     * 이미지를 삭제합니다.
     *
     * @param fileDir 이미지의 경로입니다.
     * @param image {@link ImageTableDTO} 형식으로 이미지 파일을 받습니다.
     * @return 삭제 성공 시 {@code true}가 반환되고,
     * 오류가 발생할 시 {@code false}가 반환됩니다.
     */
    public static int deleteImage(String fileDir, ImageTableDTO image) {
        log.info("deleteFile 호출");
        Path imagePath = Paths.get(fileDir);
        log.info("getting imagePath : {}", imagePath);
        //경로 존재 확인
        if(!pathExistCheckForDelete(imagePath)) {
            return 0;
        }
        //경로가 없으면 패스
        int result = 0;
        //썸네일이 존재할 때, 썸네일 삭제 시도.
        log.info("getting Thumbnail() : {}", image.getThumbnail());
        if(image.getThumbnail() != null && fileDelete(imagePath, image.getThumbnail())) {
            result += 1;
        }
        //미리보기 이미지 삭제 시도.
        log.info("getting Preview() : {}", image.getPreview());
        if(fileDelete(imagePath, image.getPreview())) {
            result += 1;
        }
        //원본 이미지 삭제 시도.
        log.info("getting Original() : {}", image.getOriginal());
        if(fileDelete(imagePath, image.getOriginal())) {
            result += 1;
        }

        log.info("deleteFile 종료");
        return result;
    }

    /**
     * 파일의 경로가 존재하는지 확인하여<br>
     * 존재하지 않는 경우 경로를 새로 생성합니다.
     * @param fileDir 존재를 확인할 경로입니다.
     * @return 경로가 존재한다면 {@code true}, 경로가 없다면 {@code false}를 반환합니다.
     */
    private static boolean pathExistCheckForDelete(Path fileDir) {
        if(!Files.exists(fileDir)) {
            log.warn("해당 경로가 존재하지 않습니다. : " + fileDir);
            return false;
        }
        return true;
    }

    /**
     * 파일을 삭제합니다.
     * @param filePath 파일의 경로입니다.
     * @param fileName 파일의 이름입니다.
     * @return 삭제 시 {@code true}를 반환하고, 실패 시 {@code false}를 반환합니다.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean fileDelete(Path filePath, String fileName) {
        try {
            log.debug("filePath : {}", filePath);
            log.info("fileName : {}", fileName);
            Path imagePath = filePath.resolve(fileName);
            log.debug("imagePath : {}", imagePath);
            Files.delete(imagePath);
        } catch (IOException e) {
            log.warn("파일을 삭제할 수 없었습니다. : " + fileName, e);
            return false;
        }
        return true;
    }
}
