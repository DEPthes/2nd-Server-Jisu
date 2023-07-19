package com.example.studyboard.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateBoardDto {

    @NotBlank
    private String title;
    @NotBlank
    private String contents;

}
