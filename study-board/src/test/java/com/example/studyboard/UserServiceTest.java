package com.example.studyboard;

import com.example.studyboard.user.domain.Role;
import com.example.studyboard.user.domain.User;
import com.example.studyboard.user.dto.UserInfoDto;
import com.example.studyboard.user.repository.UserRepository;
import com.example.studyboard.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    public void testGetMyInfo() {
        // 가상의 User 객체 생성
        String userId = "testUser";
        String username = "Test User";
        Role role = Role.USER;

        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setRole(role);

        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(user));

        UserInfoDto result = userService.getMyInfo(userId);

        // 결과 검증
        assertEquals(userId, result.getUserId());
        assertEquals(username, result.getUsername());
        assertEquals(role, result.getRole());
    }

}