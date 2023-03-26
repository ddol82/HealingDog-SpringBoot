package com.healing.healingdog.common;


import org.springframework.http.HttpStatus;

/**
 * 이미지를 관리합니다.
 * 파일 이름은 UUID를 이용하여 랜덤 생성합니다.
 *  Annotation을 사용하여 log를 통한 Logger사용이 가능합니다.
 * <pre>
 * {@code
 * ImageUtils imageUtils = new ImageUtils();
 * imageUtils.uploadImage(...);
 * }
 * </pre>
 * @since 1.0
 * @author 이진녕
 * @version 1.0
 */
public class ResponseDTO {

    private int status;
    private String message;
    private Object data;

    public ResponseDTO(HttpStatus status, String message, Object data){
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
