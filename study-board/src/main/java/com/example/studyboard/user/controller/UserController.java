package com.example.studyboard.user.controller;

import com.example.studyboard.user.dto.CheckUsernameRequest;
import com.example.studyboard.user.dto.CheckUsernameResponse;
import com.example.studyboard.user.dto.CreateUserRequest;
import com.example.studyboard.user.dto.UpdateUserDto;
import com.example.studyboard.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping("/sign")
    public void sign(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userService.sign(createUserRequest.newUser(passwordEncoder));
    }

    // 로그인
    // 아이디 중복체크
    @PostMapping("/check-id")
    public CheckUsernameResponse checkId(@RequestBody CheckUsernameRequest checkUsernameRequest) {

        boolean isNotDuplicated = userService.checkUsername(checkUsernameRequest.getUsername());
        return new CheckUsernameResponse(isNotDuplicated);
    }

    // (ROLE: ADMIN) 게시글 삭제

    // (공통)
    // 내 정보 조회
    // 내 정보 수정
    @PutMapping("/info")
    public void updateUserInfo(String username, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUserInfo(username, updateUserDto);
    }

    // 내가 쓴 게시글 확인

    // 비밀번호 변경

}
