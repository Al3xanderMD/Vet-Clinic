package com.example.MyVet.Exceptions;

public class PasswordResetTokenExpiredException extends RuntimeException {
    public PasswordResetTokenExpiredException(String token) {
        super("Password reset token expired: " + token);
    }
}
