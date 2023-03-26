package com.healing.healingdog.common;


import org.springframework.http.HttpStatus;

/**
 * data에 {@link HttpStatus Http응답}과 관련된 내용을 덧붙입니다.
 *
 * @since 1.0
 * @author 이진녕
 * @version 1.0
 */
public class ResponseDTO {

    private int status;
    private String detail;
    private String message;
    private Object data;

    public ResponseDTO(HttpStatus status, String message, Object data){
        this.status = status.value();
        this.detail = status.getReasonPhrase();
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
                ", detail=" + detail +
                ", message='" + message +
                ", data=" + data +
                '}';
    }
}
