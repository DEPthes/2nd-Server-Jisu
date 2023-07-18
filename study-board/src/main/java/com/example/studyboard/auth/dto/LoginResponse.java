package com.example.studyboard.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {
    private String tokenType;
    private String accessToken;
    private String username;
    private String role;

    public LoginResponse(String tokenType, String accessToken, String username, String role) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.username = username;
        this.role = role;
    }
}