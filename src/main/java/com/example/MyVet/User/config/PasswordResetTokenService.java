package com.example.MyVet.User.config;

import jakarta.persistence.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
public class PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Transactional
    public PasswordResetToken getPasswordResetTokenByToken(String token)
            throws EntityNotFoundException {
        PasswordResetToken passwordResetToken =
                passwordResetTokenRepository
                        .findByToken(token)
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "PasswordResetToken Not Found: " + token));

        return passwordResetToken;
    }

    @Transactional
    public void createToken(PasswordResetToken passwordResetToken) throws EntityExistsException {
        if (Boolean.TRUE.equals(
                passwordResetTokenRepository.existsByToken(passwordResetToken.getToken())))
            throw new EntityExistsException(
                    "Password Reset Token already exists: "
                            + passwordResetToken.getUser().getUsername());
        passwordResetTokenRepository.save(passwordResetToken);
    }

    public Boolean isPasswordResetTokenExpired(PasswordResetToken passwordResetToken) {
        Calendar cal = Calendar.getInstance();
        return passwordResetToken.getExpiryDate().before(cal.getTime());
    }

    @Transactional
    public Boolean existsPasswordResetTokenByToken(String token) {
        return passwordResetTokenRepository.existsByToken(token);
    }
}