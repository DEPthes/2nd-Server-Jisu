package com.example.studyboard.user.controller;

import com.example.studyboard.auth.jwt.JwtTokenProvider;
import com.example.studyboard.board.dto.BoardDto;
import com.example.studyboard.user.domain.User;
import com.example.studyboard.user.dto.*;
import com.example.studyboard.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping("/sign")
    public void sign(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userService.sign(createUserRequest.newUser(passwordEncoder));
    }

    // 아이디 중복체크
    @PostMapping("/check-id")
    public CheckUserIdResponse checkId(@RequestBody CheckUserIdRequest checkUserIdRequest) {

        boolean isNotDuplicated = userService.checkUserId(checkUserIdRequest.getUserId());
        return new CheckUserIdResponse(isNotDuplicated); // true: 사용가능
    }

    // (ROLE: ADMIN) 게시글 삭제
    // 관리자 게시글 상단고정?

    // 내 정보 조회
    @GetMapping("/info")
    public UserInfoDto getMyInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);
        String userId = jwtProvider.extractUserId(token);
        return userService.getMyInfo(userId);
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        throw new IllegalArgumentException("Invalid authorization header");
    }
    // 내 정보 수정
    @PutMapping("/info")
    public void updateUserInfo(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UpdateUserDto updateUserDto) {
        String token = extractTokenFromHeader(authorizationHeader);
        String userId = jwtProvider.extractUserId(token);
        User user = userService.findByUserId(userId);
        userService.updateUserInfo(user, updateUserDto);
    }

    // 내가 쓴 게시글 확인
    @GetMapping("/info/my-boards")
    public List<BoardDto> getMyBoards(@RequestHeader("Authorization") String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);
        String userId = jwtProvider.extractUserId(token);

        List<BoardDto> myBoards = userService.getMyBoards(userId);
        return myBoards;
    }

}
