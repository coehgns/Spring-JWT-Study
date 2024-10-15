package com.example.springjwt.domain.board.service;

import com.example.springjwt.domain.board.domain.Board;
import com.example.springjwt.domain.board.domain.BoardComment;
import com.example.springjwt.domain.board.domain.repository.BoardCommentRepository;
import com.example.springjwt.domain.board.domain.repository.BoardRepository;
import com.example.springjwt.domain.board.exception.BoardCommentAuthorMissmatchException;
import com.example.springjwt.domain.board.exception.BoardCommentNotFoundException;
import com.example.springjwt.domain.board.exception.BoardNotFoundException;
import com.example.springjwt.domain.board.presentation.dto.request.BoardCommentRequest;
import com.example.springjwt.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;
    private final BoardRepository boardRepository;
    private final UserFacade userFacade;

    public void addBoardComment(BoardCommentRequest request) {

        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        boardCommentRepository.save(
                BoardComment.builder()
                        .board(board)
                        .content(request.getContent())
                        .build()
        );
    }

    @Transactional
    public void deleteBoardComment(Long boardCommentId) {

        BoardComment boardComment = boardCommentRepository.findById(boardCommentId)
                .orElseThrow(() -> BoardCommentNotFoundException.EXCEPTION);

        if(!userFacade.currentUser().getUsername().equals(boardComment.getAuthor())) {
            throw BoardCommentAuthorMissmatchException.EXCEPTION;
        }

        boardCommentRepository.deleteById(boardCommentId);
    }

    @Transactional
    public void modifyBoardComment(Long boardCommentId, String content) {

        BoardComment boardComment = boardCommentRepository.findById(boardCommentId)
                .orElseThrow(() -> BoardCommentNotFoundException.EXCEPTION);

        if(!userFacade.currentUser().getUsername().equals(boardComment.getAuthor())) {
            throw BoardCommentAuthorMissmatchException.EXCEPTION;
        }

        boardComment.updateBoardComment(content);
    }
}
