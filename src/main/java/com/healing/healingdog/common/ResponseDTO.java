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

    /**
     * ResponseDTO의 생성자입니다.<br>
     * <pre>
     * {@code new ResponseDTO(
     *     HttpStatus.상태 - params 설명 참조,
     *     "메시지",
     *     데이터(DTO 등)
     * );}
     * </pre>
     * 의
     * 형식으로 사용합니다.<br>
     * React의 APICalls에서 result에 담기는 값입니다.
     *
     * @param status {@link HttpStatus}타입을 받습니다.<br>
     * 이 파라미터는 {@link HttpStatus#OK}처럼 사용합니다.
     * @param message 메시지를 적습니다.
     * @param data 데이터를 반환합니다.
     */
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
