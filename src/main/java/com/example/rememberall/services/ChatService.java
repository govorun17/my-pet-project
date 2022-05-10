package com.example.rememberall.services;

import com.example.rememberall.DTO.ChatDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ChatService {
    private final List<ChatDTO> chatTemp = new LinkedList<>();

    {
        chatTemp.add(new ChatDTO("Andrey", "Hello"));
        chatTemp.add(new ChatDTO("Vasiliy", "Hello"));
        chatTemp.add(new ChatDTO("Sergey", "Hello"));
        chatTemp.add(new ChatDTO("Andrey", "How are u?"));
        chatTemp.add(new ChatDTO("Vasiliy", "Fine. U?"));
        chatTemp.add(new ChatDTO("Andrey", "Fine too."));
    }

    public List<ChatDTO> getChatTemp() {
        return chatTemp;
    }

    public void addMsg(ChatDTO chatDTO) {
        chatTemp.add(chatDTO);
    }
}
