package com.example.studyboard.user.dto;

import com.example.studyboard.user.domain.Role;
import com.example.studyboard.user.domain.User;
import lombok.Getter;

@Getter
public class UserInfoDto {

    private final String userId;
    private final String username;
    private final Role role;

    public UserInfoDto(User user){
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}
