package com.example.studyboard.auth.service;

import com.example.studyboard.auth.jwt.JwtTokenProvider;
import com.example.studyboard.auth.jwt.Principal;
import com.example.studyboard.auth.dto.LoginRequest;
import com.example.studyboard.auth.dto.LoginResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final String TOKEN_TYPE = "Bearer";
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtProvider;

    private void addTokenToCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        // cookie.setSecure(true); // HTTPS 프로토콜에서만 전송

        response.addCookie(cookie);
    }

    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword(), null);
        Authentication authentication = authenticationManager.authenticate(authToken);
        Principal principal = (Principal) authentication.getPrincipal();
        String token = jwtProvider.createToken(principal);
        addTokenToCookie(response, token);
        String userId = principal.getUser().getUserId();
        String role = principal.getAuthorities().stream().findFirst().orElseThrow(() -> new IllegalArgumentException("")).getAuthority();
        return new LoginResponse(TOKEN_TYPE, token, userId, role);
    }
}
