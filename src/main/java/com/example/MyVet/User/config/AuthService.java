package com.example.MyVet.User.config;

import com.example.MyVet.Assistant.Assistant;
import com.example.MyVet.Assistant.AssistantService;
import com.example.MyVet.Exceptions.PasswordResetTokenExpiredException;
import com.example.MyVet.MedicalStaff.MedicalStaff;
import com.example.MyVet.MedicalStaff.MedicalStaffService;
import com.example.MyVet.PetOwner.PetOwner;
import com.example.MyVet.PetOwner.PetOwnerService;
import com.example.MyVet.User.JWT.JwtTokenProvider;
import com.example.MyVet.User.dto.*;
import com.example.MyVet.User.email.EmailService;
import com.example.MyVet.User.users.User;
import com.example.MyVet.User.users.UserService;
import com.example.MyVet.User.users.role.Role;
import com.example.MyVet.User.users.role.RoleService;
import com.example.MyVet.Vet.Vet;
import com.example.MyVet.Vet.VetService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordResetTokenService passwordResetTokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;
    private final PetOwnerService petOwnerService;
    private final AssistantService assistantService;
    private final MedicalStaffService medicalStaffService;
    private final VetService vetService;

    @Autowired
    private JavaMailSender mailSender;

    public UserJwtResponseDto authenticateUser(UserLoginDto userLoginDto) {

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userLoginDto.getEmail(), userLoginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateJwtToken(authentication);

            User user = (User) authentication.getPrincipal();
            return new UserJwtResponseDto(
                    jwt, user.getId(), user.getEmail(), user.getRole().getName().name());
    }

    public UserRegisterResponseDto registerUser(UserRegisterDto userRegisterDto) throws MessagingException, UnsupportedEncodingException {
        User user;
        String strRole = userRegisterDto.getRole();
        String randomCode = RandomString.make(64);

        if (userRegisterDto.getRole() == null )
            strRole = "ROLE_PetOwner";

        switch (strRole) {
            case "ROLE_ADMIN" -> {
                Role role = roleService.getRoleByName(Role.ERole.ROLE_ADMIN);
                user =
                        new User(
                                userRegisterDto.getFirstName(),
                                userRegisterDto.getLastName(),
                                userRegisterDto.getEmail(),
                                userRegisterDto.getPhone(),
                                userRegisterDto.getIcn(),
                                userRegisterDto.getAdress(),
                                encoder.encode(userRegisterDto.getPassword()));
                user.setRole(role);
                user.setVerificationCode(randomCode);
                userService.createUser(user);
            }
        case "ROLE_Assistant" -> {
            Role role = roleService.getRoleByName(Role.ERole.ROLE_Assistant);
                        user =
                    new User(
                            userRegisterDto.getFirstName(),
                            userRegisterDto.getLastName(),
                            userRegisterDto.getEmail(),
                            userRegisterDto.getPhone(),
                            userRegisterDto.getIcn(),
                            userRegisterDto.getAdress(),
                            encoder.encode(userRegisterDto.getPassword()));
            user.setRole(role);
            user.setVerificationCode(randomCode);
            userService.createUser(user);
            MedicalStaff medicalStaff = new MedicalStaff(user);
            medicalStaff.setId(user.getId());
            medicalStaffService.add(medicalStaff);
            Assistant assistant = new Assistant(medicalStaff);
            assistant.setId(medicalStaff.getId());
            assistantService.add(assistant);
        }
            case "ROLE_VET" -> {
                Role role = roleService.getRoleByName(Role.ERole.ROLE_VET);
                      user =
                        new User(
                                userRegisterDto.getFirstName(),
                                userRegisterDto.getLastName(),
                                userRegisterDto.getEmail(),
                                userRegisterDto.getPhone(),
                                userRegisterDto.getIcn(),
                                userRegisterDto.getAdress(),
                                encoder.encode(userRegisterDto.getPassword()));
                user.setRole(role);
                user.setVerificationCode(randomCode);
                userService.createUser(user);
                MedicalStaff medicalStaff = new MedicalStaff(user);
                medicalStaff.setId(user.getId());
                medicalStaffService.add(medicalStaff);
                Vet vet = new Vet(medicalStaff);
                vet.setId(medicalStaff.getId());
                vetService.add(vet);
            }
            default -> {
                Role role = roleService.getRoleByName(Role.ERole.ROLE_PetOwner);
                user =
                        new User(
                userRegisterDto.getFirstName(),
                        userRegisterDto.getLastName(),
                        userRegisterDto.getEmail(),
                        userRegisterDto.getPhone(),
                        userRegisterDto.getIcn(),
                        userRegisterDto.getAdress(),
                        encoder.encode(userRegisterDto.getPassword()));
                user.setRole(role);
                user.setVerificationCode(randomCode);
                userService.createUser(user);
                PetOwner petOwner = new PetOwner(user);
                petOwner.setId(user.getId());
                petOwnerService.add(petOwner);
            }
        }
        sendVerificationEmail(user);
        return new UserRegisterResponseDto(user.getEmail(),user.getPassword(), user.getRole().getName().name(),user.getVerificationCode(),user.getIcn(),user.getAdresses());
    }

    private void sendVerificationEmail(User user)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "alex.ionascu2001@gmail.com";
        String senderName = "Vet Connect";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName());
        String verifyURL = "http://localhost:3000/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    public boolean verify(String verificationCode) {
        User user = userService.getUserByVerificationCode(verificationCode);

        //|| user.isEnabled()
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userService.updateUser(user);
            return true;
        }
    }

    public MessageDto resetPasswordUser(String email) {
        User user = userService.getUserByEmail(email);

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(user, token);
        passwordResetTokenService.createToken(resetToken);

        emailService.sendMail(
                "alex.ionascu2001@gmail.com",
                email,
                "Reset Password",
                "http://localhost:3000/Forms/changePassword?token=" + token);

        return new MessageDto("Email has been sent.");
    }

    public MessageDto changePasswordUser(
            UserChangePasswordDto userChangePasswordDto, String token) {
        PasswordResetToken passwordResetToken =
                passwordResetTokenService.getPasswordResetTokenByToken(token);
        if (Boolean.TRUE.equals(
                passwordResetTokenService.isPasswordResetTokenExpired(passwordResetToken))) {
            throw new PasswordResetTokenExpiredException(passwordResetToken.getToken());
        }

        User user = passwordResetToken.getUser();
        user.setPassword(encoder.encode(userChangePasswordDto.getNewPassword()));
        userService.updateUser(user);
        return new MessageDto("Password has changed.");
    }
}

