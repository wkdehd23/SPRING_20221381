package com.example.demo.model.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "Board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title = "";
    @Column(name = "content", nullable = false)
    private String content = "";
    @Column(name = "user", nullable = false) // 이름
    private String user = "";
    @Column(name = "newdate", nullable = false) // 날짜
    private String newdate = "";
    @Column(name = "count", nullable = false) // 조회수
    private String count = "";
    @Column(name = "likec", nullable = false) // 좋아요
    private String likec = "";

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) { // 현재 객체 상태 업데이트
        this.title = title;
        this.content = content;
    }
}
