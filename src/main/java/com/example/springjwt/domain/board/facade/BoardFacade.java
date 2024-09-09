package com.example.springjwt.domain.board.facade;

import com.example.springjwt.domain.board.domain.Board;
import com.example.springjwt.domain.board.exception.BoardAuthorMismatchException;
import com.example.springjwt.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardFacade {
    public void authorCheck(User user, Board board) {
        if(board.getUser() != user) {
            throw BoardAuthorMismatchException.EXCEPTION;
        }
    }
}
