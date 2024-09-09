package com.example.springjwt.domain.board.service;

import com.example.springjwt.domain.board.domain.repository.BoardRepository;
import com.example.springjwt.domain.board.presentation.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SelectAllBoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponse> execute() {
        return boardRepository.findAll()
                .stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }
}
