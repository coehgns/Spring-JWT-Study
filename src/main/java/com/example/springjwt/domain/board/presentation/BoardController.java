package com.example.springjwt.domain.board.presentation;

import com.example.springjwt.domain.board.presentation.dto.request.BoardRequest;
import com.example.springjwt.domain.board.presentation.dto.response.BoardResponse;
import com.example.springjwt.domain.board.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final AddBoardService addBoardService;
    private final DeleteBoardService deleteBoardService;
    private final ModifyBoardService modifyBoardService;
    private final SelectBoardService selectBoardService;
    private final SelectAllBoardService selectAllBoardService;

    @PostMapping
    public void addBoard(@RequestBody BoardRequest request) {
        addBoardService.execute(request);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        deleteBoardService.execute(boardId);
    }

    @PatchMapping("/{boardId}")
    public void modifyBoard(@PathVariable Long boardId, BoardRequest request) {
        modifyBoardService.execute(boardId, request);
    }

    @GetMapping("/{boardId}")
    public BoardResponse selectBoard(@PathVariable Long boardId) {
        return selectBoardService.execute(boardId);
    }

    @GetMapping("/allBoard")
    public List<BoardResponse> selectAllBoard() {
        return selectAllBoardService.execute();
    }
}
