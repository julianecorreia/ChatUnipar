package br.unipar.frameworksweb.chatunipar;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("chat-websocket")
public class ChatController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage message) {
        System.out.println(message.getSender() + " disse: " + message.getContent());
        return message;
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        System.out.println(message.getSender() + " entrou.");
        return message;
    }

    @MessageMapping("/leaveUser")
    @SendTo("/topic/public")
    public ChatMessage leaveUser(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println(message.getSender() + " saiu.");
        return message;
    }

}
