package com.healing.healingdog.login.model.dto;

public class MessagesDTO {
    private String to;
    private String content;

    public MessagesDTO() {
    }

    public MessagesDTO(String to, String content) {
        this.to = to;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "to='" + to + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
