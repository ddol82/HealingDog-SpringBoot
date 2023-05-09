package com.healing.healingdog.login.model.dto;

public class RequestDTO {
    private String recipientPhoneNumber;
    private String title;
    private String content;

    public RequestDTO() {
    }

    public RequestDTO(String recipientPhoneNumber, String title, String content) {
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.title = title;
        this.content = content;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "recipientPhoneNumber='" + recipientPhoneNumber + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
