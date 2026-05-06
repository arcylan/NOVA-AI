package com.personal.assistant.Controller;

import com.personal.assistant.Model.Chat;
import com.personal.assistant.Model.User;
import com.personal.assistant.Repository.ChatRepository;
import com.personal.assistant.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @PostMapping("/create")
    public ResponseEntity<Long> createChat(Authentication auth){
        String name = auth.getName();
        User userName = userRepository.findByUsername(name);
        if(userName==null){
            throw new RuntimeException("user not found");
        }
        Chat chat = new Chat();
        chat.setUser(userName);
        chatRepository.save(chat);
        return ResponseEntity.ok(chat.getId());
    }


}
