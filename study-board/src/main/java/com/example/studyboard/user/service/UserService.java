package com.example.studyboard.user.service;

import com.example.studyboard.user.domain.User;
import com.example.studyboard.user.dto.UpdateUserDto;
import com.example.studyboard.user.dto.UserInfoDto;
import com.example.studyboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void sign(User user) {
        userRepository.save(user);
    }

    public boolean checkUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public UserInfoDto getMyInfo(User user) {
        String username = user.getUsername();
        User newUser = userRepository.findByUser(username);

        return new UserInfoDto(newUser);
    }

    public void updateUserInfo(String username, UpdateUserDto updateUserDto) {
        User user = userRepository.findByUser(username);

        updateUserDto.displayName().ifPresent(user::updateDisplayName);
    }
}
