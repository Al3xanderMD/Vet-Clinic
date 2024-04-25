package com.example.MyVet.User.email;


import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmailServiceTest {

    @Test
    void sendMailTest() throws Exception {
        // Given
        JavaMailSender mockMailSender = Mockito.mock(JavaMailSender.class);
        EmailService emailService = new EmailService(mockMailSender);

        String from = "example@example.com";
        String to = "test@test.com";
        String subject = "Test email";
        String content = "This is a test email";

        SimpleMailMessage expectedMail = new SimpleMailMessage();
        expectedMail.setFrom(from);
        expectedMail.setTo(to);
        expectedMail.setSubject(subject);
        expectedMail.setText(content);

        // When
        Mockito.doNothing().when(mockMailSender).send(Mockito.any(SimpleMailMessage.class));

        // Then
        emailService.sendMail(from, to, subject, content);

        // Verify
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        Mockito.verify(mockMailSender).send(captor.capture());

        SimpleMailMessage actualMail = captor.getValue();
        assertThat(actualMail).usingRecursiveComparison().isEqualTo(expectedMail);
    }
}







