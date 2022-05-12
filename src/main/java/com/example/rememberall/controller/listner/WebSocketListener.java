package com.example.rememberall.controller.listner;

import com.example.rememberall.DTO.ChatDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
public class WebSocketListener {
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketListener.class);

    private SimpMessageSendingOperations messageTemplate;

    @Autowired
    private void setMessageTemplate(SimpMessageSendingOperations messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        LOG.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("username");

        if(username != null) {
            LOG.info("User Disconnected : " + username);

            ChatDTO chatDTO = new ChatDTO(username, "", ChatDTO.MessageType.LEAVE);

            messageTemplate.convertAndSend("/topic/publicChatRoom", chatDTO);
        }
    }
}
