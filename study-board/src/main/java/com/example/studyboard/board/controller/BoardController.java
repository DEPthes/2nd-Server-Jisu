package com.example.studyboard.board.controller;

import com.example.studyboard.auth.jwt.JwtTokenProvider;
import com.example.studyboard.board.domain.Board;
import com.example.studyboard.board.dto.BoardDto;
import com.example.studyboard.board.dto.CreateBoardDto;
import com.example.studyboard.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private  final JwtTokenProvider jwtProvider;

    // 게시글 작성
    @PostMapping("/new")
    public void createBoard(@RequestHeader("Authorization") String authorizationHeader,
                            @RequestBody @Valid CreateBoardDto createBoardDto) {
        String token = extractTokenFromHeader(authorizationHeader);
        String userId = jwtProvider.extractUserId(token);
        boardService.createBoard(userId, createBoardDto);
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        throw new IllegalArgumentException("Invalid authorization header");
    }

    // 게시글 전체 목록 조회
    @GetMapping
    public List<BoardDto> viewAllBoard() {
        return boardService.viewAllBoard();
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public BoardDto getBoardDetails(@PathVariable Long id) {
        return boardService.getBoardDetails(id);
    }

    // 게시글 수정
    // 게시글 삭제
}
