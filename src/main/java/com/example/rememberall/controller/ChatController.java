package com.example.rememberall.controller;

import com.example.rememberall.DTO.ChatDTO;
import com.example.rememberall.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private ChatService chatService;

    @Autowired
    private void setAutowired(
            ChatService chatService
    ) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public ModelAndView sendMessage(
        Model model,
        @ModelAttribute ChatDTO chatDTO
    ) {
        chatService.addMsg(chatDTO);
        return new ModelAndView("redirect:/");
    }
}
