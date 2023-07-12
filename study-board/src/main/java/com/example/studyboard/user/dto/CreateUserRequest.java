package com.example.studyboard.user.dto;

import com.example.studyboard.user.domain.Role;
import com.example.studyboard.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class CreateUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String displayName;

    @NotBlank
    private String role;

    public User newUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .displayName(displayName)
                .role(Role.USER)
                .build();
    }
}
