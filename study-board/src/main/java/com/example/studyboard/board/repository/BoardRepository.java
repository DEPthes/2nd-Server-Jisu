package com.example.studyboard.board.repository;

import com.example.studyboard.board.domain.Board;
import com.example.studyboard.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByUserUserId(String userId);
}
