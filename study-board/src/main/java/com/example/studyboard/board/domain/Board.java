package com.example.studyboard.board.domain;

import com.example.studyboard.userboard.domain.UserBoard;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<UserBoard> userBoards = new ArrayList<>();

    @Builder
    Board(String title, String contents, LocalDateTime creationDate) {
        this.title = title;
        this.contents = contents;
        this.creationDate = creationDate;
    }

}
