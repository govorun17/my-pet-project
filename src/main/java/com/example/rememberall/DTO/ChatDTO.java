package com.example.rememberall.DTO;

public class ChatDTO {
    private String name;
    private String message;

    public ChatDTO() {
    }

    public ChatDTO(String name, String message) {
        this.name = name;
        this.message = message;
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
