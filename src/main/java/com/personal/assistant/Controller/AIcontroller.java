package com.personal.assistant.Controller;

import com.personal.assistant.Service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AIcontroller {

    @Autowired
    private ChatService chatService;

    @GetMapping("/{chatId}/{message}")
    public String getResponse(@PathVariable Long chatId, @PathVariable String message){
        String response = chatService.chatProcess(chatId, message);
        return response;
    }
}

