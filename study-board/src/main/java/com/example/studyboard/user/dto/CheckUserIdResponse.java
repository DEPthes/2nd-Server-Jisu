package com.example.studyboard.user.dto;

import lombok.Getter;

@Getter
public class CheckUserIdResponse {
    private final boolean isNotDuplicated;

    public CheckUserIdResponse(boolean isNotDuplicated) {
        this.isNotDuplicated = isNotDuplicated;
    }
}
