package com.example.MyVet.User.email;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendMail(String from, String to, String subject, String content) {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setFrom(from);
        email.setTo(to);

        email.setSubject(subject);
        email.setText(content);

        mailSender.send(email);
    }

}
