package com.example.MyVet.User.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MailConfigTest {
    @Mock
    private JavaMailSenderImpl mailSender;

    private MailConfig mailConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mailConfig = new MailConfig();
    }

    @Test
    public void testJavaMailSender() {
        JavaMailSender javaMailSender = mailConfig.javaMailSender();

        assertNotNull(javaMailSender);
        assertNotEquals("smtp.gmail.com", mailSender.getHost());
        assertNotEquals(465, mailSender.getPort());
        assertNotEquals("alex.ionascu2001@gmail.com", mailSender.getUsername());
        assertNotEquals("jjgqwiaqiwlsyiuj", mailSender.getPassword());

        Properties props = ((JavaMailSenderImpl) javaMailSender).getJavaMailProperties();
        assertNotNull(props);
        assertEquals("true", props.getProperty("mail.smtp.auth"));
        assertEquals("true", props.getProperty("mail.smtp.ssl.enable"));
    }

    @Test
    public void testConstructor() {
        MailConfig mailConfig = new MailConfig();

        assertNotNull(mailConfig);
    }
}


