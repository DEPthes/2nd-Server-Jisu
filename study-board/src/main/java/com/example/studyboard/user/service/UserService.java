package com.example.studyboard.user.service;

import com.example.studyboard.board.domain.Board;
import com.example.studyboard.board.dto.BoardDto;
import com.example.studyboard.board.repository.BoardRepository;
import com.example.studyboard.user.domain.User;
import com.example.studyboard.user.dto.UpdateUserDto;
import com.example.studyboard.user.dto.UserInfoDto;
import com.example.studyboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public void sign(User user) {
        userRepository.save(user);
    }

    public boolean checkUserId(String userId) {
        return userRepository.findByUserId(userId).isEmpty();
    }

    public UserInfoDto getMyInfo(String userId) {
        User newUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        return new UserInfoDto(newUser);
    }

    public List<BoardDto> getMyBoards(String userId) {
        List<Board> boards = boardRepository.findByUserUserId(userId);
        return boards.stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUserInfo(User user, UpdateUserDto updateUserDto) {
        updateUserDto.username().ifPresent(user::updateUsername);
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException(""));
    }
}
