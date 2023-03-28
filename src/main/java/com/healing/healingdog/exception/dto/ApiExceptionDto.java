package com.healing.healingdog.exception.dto;

import org.springframework.http.HttpStatus;



public class ApiExceptionDto {
    private int state;
    private String detail;
    private String message;

    public ApiExceptionDto(HttpStatus state, String message){
        this.state = state.value();
        this.detail = state.getReasonPhrase();
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    @Override
    public String toString() {
        return "ApiExceptionDto{" +
                "state=" + state +
                ", detail=" + detail +
                ", message='" + message + '\'' +
                '}';
    }
}