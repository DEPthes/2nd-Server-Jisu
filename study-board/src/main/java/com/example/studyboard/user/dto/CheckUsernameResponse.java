package com.example.studyboard.user.dto;

import lombok.Getter;

@Getter
public class CheckUsernameResponse {
    private final boolean isNotDuplicated;

    public CheckUsernameResponse(boolean isNotDuplicated) {
        this.isNotDuplicated = isNotDuplicated;
    }
}
