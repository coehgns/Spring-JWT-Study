package com.example.springjwt.domain.board.service;

import com.example.springjwt.domain.board.domain.Board;
import com.example.springjwt.domain.board.domain.repository.BoardRepository;
import com.example.springjwt.domain.board.exception.BoardAuthorMismatchException;
import com.example.springjwt.domain.board.exception.BoardNotFoundException;
import com.example.springjwt.domain.board.presentation.dto.request.BoardCreateRequest;
import com.example.springjwt.domain.board.presentation.dto.request.BoardUpdateRequest;
import com.example.springjwt.domain.board.presentation.dto.response.BoardResponse;
import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserFacade userFacade;

    @Transactional
    public void addBoard(BoardCreateRequest request) {

        User currentUser = userFacade.currentUser();

        boardRepository.save(
                Board.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .user(currentUser)
                        .author(currentUser.getUsername())
                        .build()
        );
    }

    @Transactional
    public void deleteBoard(Long boardId) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        if(!userFacade.currentUser().equals(board.getUser())) {
            throw BoardAuthorMismatchException.EXCEPTION;
        }

        boardRepository.deleteById(boardId);
    }

    @Transactional
    public void modifyBoard(Long boardId, BoardUpdateRequest request) {

        User currentUser = userFacade.currentUser();

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        if(currentUser.equals(board.getUser())) {
            throw BoardAuthorMismatchException.EXCEPTION;
        }

        board.modifyBoard(request.getTitle(), request.getContent());
    }

    public List<BoardResponse> selectAllBoard() {
        return boardRepository.findAll()
                .stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardResponse selectBoard(Long boardId) {
        Board board  = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        return new BoardResponse(board);
    }
}
