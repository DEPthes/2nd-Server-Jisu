package com.example.studyboard.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CheckUserIdRequest {

    @NotBlank
    private String userId;
}
