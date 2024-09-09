package com.example.springjwt.domain.board.service;

import com.example.springjwt.domain.board.domain.Board;
import com.example.springjwt.domain.board.domain.repository.BoardRepository;
import com.example.springjwt.domain.board.exception.BoardNotFoundException;
import com.example.springjwt.domain.board.facade.BoardFacade;
import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBoardService {

    private final BoardRepository boardRepository;
    private final UserFacade userFacade;
    private final BoardFacade boardFacade;

    public void execute(Long boardId) {
        User currentUser = userFacade.currentUser();
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        boardFacade.authorCheck(currentUser, board);

        boardRepository.deleteById(boardId);
    }
}
