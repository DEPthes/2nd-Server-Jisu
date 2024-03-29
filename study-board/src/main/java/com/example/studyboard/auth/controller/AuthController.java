package com.example.studyboard.auth.controller;

import com.example.studyboard.auth.dto.LoginRequest;
import com.example.studyboard.auth.dto.LoginResponse;
import com.example.studyboard.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response) {
        return authService.login(loginRequest, response);
    }
}
