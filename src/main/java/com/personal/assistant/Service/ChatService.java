package com.personal.assistant.Service;

import com.personal.assistant.Model.Chat;
import com.personal.assistant.Model.Message;
import com.personal.assistant.Repository.ChatRepository;
import com.personal.assistant.Repository.MessageRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private ChatRepository chatRepository;
    private MessageRepository messageRepository;
    private ChatClient chatClient;

    public ChatService(MessageRepository messageRepository,
                       ChatRepository chatRepository,
                       ChatClient chatClient) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.chatClient = chatClient;
    }

    public String chatProcess(Long chatId, String userInput){

        //GET THE CHAT
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("chat not found!"));
        //SAVE THE MESSAGE OF USER
        Message message = new Message();
        message.setChat(chat);
        message.setRole("USER");
        message.setCreatedAt(LocalDateTime.now());
        message.setContent(userInput);
        messageRepository.save(message);

        //FETCH LAST MESSAGES
        List<Message> messages = messageRepository.findByChatIdOrderByCreatedAtAsc(chatId);


        List<Message> messages10 = messages;
        if(messages.size()>10){
             messages10 = messages.subList(messages.size() - 10, messages.size());
        }
        //CONVERT TO AI MESSAGE
        List<org.springframework.ai.chat.messages.Message> aiMessages =
                messages10.stream()
                        .map(msg -> {
                            if ("USER".equals(msg.getRole())) {
                                return new UserMessage(msg.getContent());
                            } else {
                                return new AssistantMessage(msg.getContent());
                            }
                        })
                        .collect(Collectors.toList());


    String content = chatClient.prompt()
            .messages(aiMessages)
            .call()
            .content();




        Message mes = new Message();
        mes.setChat(chat);
        mes.setRole("ASSISTANT");
        mes.setContent(content);
        mes.setCreatedAt(LocalDateTime.now());
        messageRepository.save(mes);

        return content;
    }
}
