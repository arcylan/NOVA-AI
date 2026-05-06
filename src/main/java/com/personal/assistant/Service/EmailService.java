package com.personal.assistant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void SendWelcomeMail(String email){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Welcome To NOVA AI ");
        mailMessage.setText("Hey! Thanks for registering with NOVA AI.Enjoy your AI experience! Reply to this mail if found any Quries!");

        mailSender.send(mailMessage);
    }
}
