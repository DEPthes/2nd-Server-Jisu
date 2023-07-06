package com.example.studyboard.user.domain;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("관리자"),
    USER("사용자");

    private final String roleName;
    Role(String roleName) { this.roleName = roleName; }
}
