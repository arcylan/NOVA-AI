package com.personal.assistant.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    @Column(columnDefinition ="TEXT")
    private String content;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name="chat_id")
    private Chat chat;
}
