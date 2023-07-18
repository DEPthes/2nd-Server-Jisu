package com.example.studyboard.user.dto;

import com.example.studyboard.user.domain.Role;
import com.example.studyboard.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class CreateUserRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

    public User newUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .username(username)
                .role(Role.USER)
                .build();
    }
}
