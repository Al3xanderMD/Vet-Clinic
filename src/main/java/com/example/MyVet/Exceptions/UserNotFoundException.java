package com.example.MyVet.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email) {
        super("User not found with email: " + email);
    }

}
