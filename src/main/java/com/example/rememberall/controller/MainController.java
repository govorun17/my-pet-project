package com.example.rememberall.controller;

import com.example.rememberall.DTO.ChatDTO;
import com.example.rememberall.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
    private ChatService chatService;

    @Autowired
    private void setAutowired(
            ChatService chatService
    ) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public ModelAndView helloPage(Model model) {
        model.addAttribute("title", "Привет, Овечка!");
        model.addAttribute("chatDTO", new ChatDTO());
        model.addAttribute("messageList", chatService.getChatTemp());
        return new ModelAndView("index");
    }
}
