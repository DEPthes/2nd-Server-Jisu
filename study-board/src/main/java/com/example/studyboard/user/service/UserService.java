package com.example.studyboard.user.service;

import com.example.studyboard.auth.jwt.JwtTokenProvider;
import com.example.studyboard.user.domain.User;
import com.example.studyboard.user.dto.UpdateUserDto;
import com.example.studyboard.user.dto.UserInfoDto;
import com.example.studyboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void sign(User user) {
        userRepository.save(user);
    }

    public boolean checkUserId(String userId) {
        return userRepository.findByUserId(userId).isEmpty();
    }

    public UserInfoDto getMyInfo(String userId) {
        User newUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return new UserInfoDto(newUser);
    }

    public void updateUserInfo(User user, UpdateUserDto updateUserDto) {
        updateUserDto.username().ifPresent(user::updateUsername);
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException(""));
    }
}
