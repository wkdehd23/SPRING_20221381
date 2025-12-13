package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.*;
import com.example.demo.model.repository.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
    @Autowired
    private final BlogRepository blogRepository;

    public List<Board> findAll() {
        return blogRepository.findAll();
    }
    
    public Page<Board> findAll(Pageable pageable) {
    return blogRepository.findAll(pageable);
    }

    public Optional<Board> findById(Long id) {
        return blogRepository.findById(id);
    }
    
    public Page<Board> searchByKeyword(String keyword, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    } // LIKE 검색 제공(대소문자 무시)

    public Board save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public void update(Long id, AddArticleRequest request) {
        Optional<Board> optionalBoard = blogRepository.findById(id); // 단일 글 조회
        optionalBoard.ifPresent(board -> {
            board.update(request.getTitle(), request.getContent()); //값을 수정
            blogRepository.save(board); // Board 객체에 저장
        });
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

}
