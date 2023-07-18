package com.example.studyboard.board.domain;

import com.example.studyboard.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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
    @CreatedDate
    private LocalDateTime creationDate;

    @Builder
    Board(String title, String contents, LocalDateTime creationDate) {
        this.title = title;
        this.contents = contents;
        this.creationDate = creationDate;
    }

}
