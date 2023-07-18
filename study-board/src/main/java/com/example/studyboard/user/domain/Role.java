package com.example.studyboard.user.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Role {

    ADMIN("관리자", Arrays.asList("ROLE_ADMIN")),
    USER("사용자", Arrays.asList("ROLE_USER"));

    private final String roleName;
    Role(String roleName, List<String> authorities) {
        this.roleName = roleName;
        this.authorities = authorities;
    }

    private final List<String> authorities;
}
