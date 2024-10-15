package com.example.springjwt.domain.board.presentation;

import com.example.springjwt.domain.board.presentation.dto.request.BoardCommentRequest;
import com.example.springjwt.domain.board.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board-comment")
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @PostMapping
    public void addBoardComment(@RequestBody BoardCommentRequest request) {
        boardCommentService.addBoardComment(request);
    }

    @PutMapping("/{boardCommentId}")
    public void modifyBoardComment(@RequestBody @PathVariable Long boardCommentId, String content) {
        boardCommentService.modifyBoardComment(boardCommentId, content);
    }

    @DeleteMapping("/{boardCommentId}")
    public void deleteBoardComment(@RequestBody @PathVariable Long boardCommentId) {
        boardCommentService.deleteBoardComment(boardCommentId);
    }
}
