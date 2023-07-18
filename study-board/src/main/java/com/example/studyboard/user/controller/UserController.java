package com.example.studyboard.user.controller;

import com.example.studyboard.user.domain.User;
import com.example.studyboard.user.dto.*;
import com.example.studyboard.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping("/user/sign")
    public void sign(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userService.sign(createUserRequest.newUser(passwordEncoder));
    }

    // 로그인
    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    // 아이디 중복체크
    @PostMapping("/user/check-id")
    public CheckUserIdResponse checkId(@RequestBody CheckUserIdRequest checkUserIdRequest) {

        boolean isNotDuplicated = userService.checkUserId(checkUserIdRequest.getUserId());
        return new CheckUserIdResponse(isNotDuplicated);
    }

    // (ROLE: ADMIN) 게시글 삭제
    // 관리자 게시글 상단고정?

    // (공통)
    // 내 정보 조회
    @GetMapping("/user/info")
    public UserInfoDto getMyInfo(User user) {
        return userService.getMyInfo(user);
    }

    // 내 정보 수정
    @PutMapping("/user/info")
    public void updateUserInfo(String userId, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUserInfo(userId, updateUserDto);
    }

    // 내가 쓴 게시글 확인

    // 비밀번호 변경

}
