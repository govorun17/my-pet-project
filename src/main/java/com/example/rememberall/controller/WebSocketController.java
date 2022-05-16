package com.example.rememberall.controller;

import com.example.rememberall.DTO.ChatDTO;
import com.example.rememberall.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Objects;

@Controller
public class WebSocketController {
    private ChatService chatService;

    @Autowired
    private void setAutowired(
            ChatService chatService
    ) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/chatRoom")
    public ChatDTO sendMessage(@Payload ChatDTO chatDTO) {
        chatService.addMsg(chatDTO);
        return chatDTO;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/chatRoom")
    public ChatDTO addUser(@Payload ChatDTO chatDTO, SimpMessageHeaderAccessor headerAccessor) {
        chatDTO.setName("user");
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatDTO.getName());
        return chatDTO;
    }
}
