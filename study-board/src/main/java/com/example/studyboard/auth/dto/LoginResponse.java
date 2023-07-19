package com.example.studyboard.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {
    private String tokenType;
    private String accessToken;
    private String userId;
    private String role;

    public LoginResponse(String tokenType, String accessToken, String userId, String role) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.userId = userId;
        this.role = role;
    }
}