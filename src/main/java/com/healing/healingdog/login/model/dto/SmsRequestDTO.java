package com.healing.healingdog.login.model.dto;

import java.util.List;

public class SmsRequestDTO {
    private String type;
    private String contentType;
    private String countryCode;
    private String from;
    private String content;
    private List<MessagesDTO> messages;

    public SmsRequestDTO() {
    }

    public SmsRequestDTO(String type, String contentType, String countryCode, String from, String content, List<MessagesDTO> messages) {
        this.type = type;
        this.contentType = contentType;
        this.countryCode = countryCode;
        this.from = from;
        this.content = content;
        this.messages = messages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MessagesDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesDTO> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "SmsRequestDTO{" +
                "type='" + type + '\'' +
                ", contentType='" + contentType + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", from='" + from + '\'' +
                ", content='" + content + '\'' +
                ", messages=" + messages +
                '}';
    }
}
