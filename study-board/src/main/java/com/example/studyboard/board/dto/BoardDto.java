package com.example.studyboard.board.dto;

import com.example.studyboard.board.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDto {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private LocalDateTime creationDate;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUser().getUsername();
        this.contents = board.getContents();
        this.creationDate = board.getCreationDate();
    }
}