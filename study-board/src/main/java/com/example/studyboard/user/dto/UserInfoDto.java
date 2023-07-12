package com.example.studyboard.user.dto;

import com.example.studyboard.user.domain.Role;
import com.example.studyboard.user.domain.User;
import lombok.Builder;

public class UserInfoDto {

    private final String username;
    private final String displayName;
    private final Role role;

    @Builder
    public UserInfoDto(User user){
        this.username = user.getUsername();
        this.displayName = user.getDisplayName();
        this.role = user.getRole();
    }
}
