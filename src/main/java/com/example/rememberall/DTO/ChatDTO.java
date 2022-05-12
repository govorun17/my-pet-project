package com.example.rememberall.DTO;

public class ChatDTO {
    private String name;
    private String message;
    private MessageType messageType;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    public ChatDTO() {
    }

    public ChatDTO(String name, String message, MessageType messageType) {
        this.name = name;
        this.message = message;
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
