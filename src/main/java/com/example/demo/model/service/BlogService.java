package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.*;
import com.example.demo.model.repository.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
    @Autowired
    // private final BlogRepository blogRepository;
    private final BoardRepository boardRepository;

    // public List<Article> findAll() {
    //     return blogRepository.findAll();
    // }
    
    // public Optional<Article> findById(Long id) { // 게시판 특정 글 조회
    //     return blogRepository.findById(id);
    // }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public Board save(AddBoardRequest request) {
        // DTO가없는경우이곳에직접구현가능
        // public ResponseEntity<Article> addArticle(@RequestParam String title, @RequestParam String content) {
        // Article article = Article.builder()
        // .title(title)
        // .content(content)
        // .build();
        return boardRepository.save(request.toEntity());
    }

    public void update(Long id, AddBoardRequest request) {
        Optional<Board> optionalBoard = boardRepository.findById(id); // 단일 글 조회
        optionalBoard.ifPresent(board -> {
            board.update(request.getTitle(), request.getContent()); //값을 수정
            boardRepository.save(board); // Board 객체에 저장
        });
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

}
