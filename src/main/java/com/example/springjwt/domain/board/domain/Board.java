package com.example.springjwt.domain.board.domain;

import com.example.springjwt.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User user;

    @Column(name = "title", length = 30)
    private String title;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "author")
    private String author;

    @OneToMany(mappedBy = "board")
    private List<BoardComment> boardCommentList = new ArrayList<>();

    public void modifyBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Builder
    public Board(String title, String content, User user, String author) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.author = author;
    }
}
