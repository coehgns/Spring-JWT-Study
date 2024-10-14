package com.example.springjwt.domain.board.domain.repository;

import com.example.springjwt.domain.board.domain.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
}
