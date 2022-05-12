package com.example.rememberall.controller;

import com.example.rememberall.DTO.ChatDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class WebSocketController {
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/chatRoom")
    public ChatDTO sendMessage(@Payload ChatDTO chatDTO) {
        return chatDTO;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/chatRoom")
    public ChatDTO addUser(@Payload ChatDTO chatDTO, SimpMessageHeaderAccessor headerAccessor) {
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatDTO.getMessage());
        return chatDTO;
    }
}
