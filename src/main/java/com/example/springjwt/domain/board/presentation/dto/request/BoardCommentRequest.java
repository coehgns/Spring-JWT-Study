package com.example.springjwt.domain.board.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCommentRequest {
    private Long boardId;
    private String content;
}
