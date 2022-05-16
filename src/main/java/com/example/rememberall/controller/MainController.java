package com.example.rememberall.controller;

import com.example.rememberall.DTO.ChatDTO;
import com.example.rememberall.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    public ModelAndView helloPage(
            HttpServletRequest request,
            Model model
    ) {
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            username = username.trim();
            model.addAttribute("isLogin", true);
            model.addAttribute("username", username);
        }
        else {
            model.addAttribute("isLogin", false);
        }
        model.addAttribute("title", "Привет, Овечка!");
        return new ModelAndView("index");
    }

    @GetMapping("/logout")
    public ModelAndView logout(
            HttpServletRequest request
    ) {
        request.getSession(true).invalidate();

        return new ModelAndView("redirect:/");
    }
}
