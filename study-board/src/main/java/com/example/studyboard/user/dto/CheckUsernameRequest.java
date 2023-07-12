package com.example.studyboard.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CheckUsernameRequest {

    @NotBlank
    private String username;
}
