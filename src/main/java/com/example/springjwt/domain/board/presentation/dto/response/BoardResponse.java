package com.example.springjwt.domain.board.presentation.dto.response;

import com.example.springjwt.domain.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponse {

    private String username;

    private String title;

    private String content;

    public BoardResponse(Board board) {
        username = board.getUser().getUsername();
        title = board.getTitle();
        content = board.getContent();
    }
}