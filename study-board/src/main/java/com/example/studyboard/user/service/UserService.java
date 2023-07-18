package com.example.studyboard.user.service;

import com.example.studyboard.JwtTokenProvider;
import com.example.studyboard.user.domain.User;
import com.example.studyboard.user.dto.LoginRequest;
import com.example.studyboard.user.dto.LoginResponse;
import com.example.studyboard.user.dto.UpdateUserDto;
import com.example.studyboard.user.dto.UserInfoDto;
import com.example.studyboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private static final String TOKEN_TYPE = "Bearer";
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtProvider;

    public void sign(User user) {
        userRepository.save(user);
    }

    public boolean checkUserId(String userId) {
        return userRepository.findByUserId(userId).isEmpty();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        String token = jwtProvider.createToken(loginRequest.getUserId());
        return new LoginResponse(TOKEN_TYPE, token);
    }
    public UserInfoDto getMyInfo(User user) {
        String userId = user.getUserId();
        User newUser = userRepository.findByUser(userId);

        return new UserInfoDto(newUser);
    }

    public void updateUserInfo(String userId, UpdateUserDto updateUserDto) {
        User user = userRepository.findByUser(userId);

        updateUserDto.username().ifPresent(user::updateUsername);
    }
}
