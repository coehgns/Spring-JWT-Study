package com.example.springjwt.domain.board.presentation;

import com.example.springjwt.domain.board.presentation.dto.request.BoardCreateRequest;
import com.example.springjwt.domain.board.presentation.dto.request.BoardUpdateRequest;
import com.example.springjwt.domain.board.presentation.dto.response.BoardResponse;
import com.example.springjwt.domain.board.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void addBoard(@RequestBody BoardCreateRequest request) {
        boardService.addBoard(request);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
    }

    @PatchMapping("/{boardId}")
    public void modifyBoard(@PathVariable Long boardId, BoardUpdateRequest request) {
        boardService.modifyBoard(boardId, request);
    }

    @GetMapping("/{boardId}")
    public BoardResponse selectBoard(@PathVariable Long boardId) {
        return boardService.selectBoard(boardId);
    }

    @GetMapping
    public List<BoardResponse> selectAllBoard() {
        return boardService.selectAllBoard();
    }
}
