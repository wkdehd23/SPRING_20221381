package com.example.demo.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.Article;
import com.example.demo.model.repository.BlogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
    @Autowired
    private final BlogRepository blogRepository;
    
    public List<Article> findAll() {
        return blogRepository.findAll();
    }
    
    public Article save(AddArticleRequest request) {
        // DTO가없는경우이곳에직접구현가능
        // public ResponseEntity<Article> addArticle(@RequestParam String title, @RequestParam String content) {
        // Article article = Article.builder()
        // .title(title)
        // .content(content)
        // .build();
        return blogRepository.save(request.toEntity());
    }
}
