package com.example.studyboard.board.service;

import com.example.studyboard.board.domain.Board;
import com.example.studyboard.board.dto.BoardDto;
import com.example.studyboard.board.dto.CreateBoardDto;
import com.example.studyboard.board.repository.BoardRepository;
import com.example.studyboard.user.domain.User;
import com.example.studyboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public Board createBoard(String userId, CreateBoardDto createBoardDto) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        Board newBoard = Board.builder()
                .title(createBoardDto.getTitle())
                .contents(createBoardDto.getContents())
                .creationDate(LocalDateTime.now())
                .user(user)
                .build();

        return boardRepository.save(newBoard);
    }

    // 전체 게시글 목록 조회
    public List<BoardDto> viewAllBoard() {
        List<Board> allBoards = boardRepository.findAll();
        return allBoards.stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());
    }

    public BoardDto getBoardDetails(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        return new BoardDto(board);
    }
}
