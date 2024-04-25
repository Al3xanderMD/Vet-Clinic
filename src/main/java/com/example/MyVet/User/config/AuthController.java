package com.example.MyVet.User.config;

import com.example.MyVet.User.dto.*;

import com.example.MyVet.User.email.EmailService;
import com.example.MyVet.User.users.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseBody
    @PostMapping("/signin")

    @Operation(
            summary = "Signin summary",
            tags = {"Authentication"},
             responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content =
                            @Content(
                                    schema = @Schema(implementation = UserJwtResponseDto.class),
                                    mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema())),
            })
    @ResponseStatus(HttpStatus.CREATED)
    public UserJwtResponseDto authenticateUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        return authService.authenticateUser(userLoginDto);
    }

    @ResponseBody
    @PostMapping("/signup")
    @Operation(
            summary = "Signup summary",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content =
                            @Content(
                                    schema =
                                    @Schema(
                                            implementation =
                                                    UserRegisterResponseDto.class),
                                    mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema()))
            })
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponseDto registerUser(
            @Valid @RequestBody UserRegisterDto userRegisterDto) throws MessagingException, UnsupportedEncodingException {
        return authService.registerUser(userRegisterDto);
    }

    @GetMapping("/verify")
    @Operation(summary = "Verify Email", tags = {"Authentication"})
    public String verifyUser(@Param("code") String code) {
        if (authService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @ResponseBody
    @PostMapping("/resetPassword")
    @Operation(
            summary = "Reset password summary",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content =
                            @Content(
                                    schema = @Schema(implementation = MessageDto.class),
                                    mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema()))
            })
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto resetPasswordUser(@RequestParam("email") String email) {
        return authService.resetPasswordUser(email);
    }

    @ResponseBody
    @PostMapping("/changePassword")
    @Operation(
            summary = "Change password summary",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content =
                            @Content(
                                    schema = @Schema(implementation = MessageDto.class),
                                    mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema()))
            })
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto changePasswordUser(
            @Valid @RequestBody UserChangePasswordDto userChangePasswordDto,
            @RequestParam("token") String token) {
        return authService.changePasswordUser(userChangePasswordDto, token);
    }
}
