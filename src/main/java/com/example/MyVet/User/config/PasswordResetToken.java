package com.example.MyVet.User.config;

import com.example.MyVet.User.users.User;
import jakarta.persistence.*;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "reset_password_tokens")
public class PasswordResetToken {

    private static final int EXPIRATION = 30;

    @Id
    private String id;

    private String token;

    @DBRef
    private User user;
    private Date expiryDate;

    public PasswordResetToken(User user, String token) {
        this.user = user;
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
