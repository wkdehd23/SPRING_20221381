// package com.example.demo.controller;

// import com.example.demo.model.domain.Article;
// import com.example.demo.model.service.AddArticleRequest;
// import com.example.demo.model.service.BlogService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RequiredArgsConstructor
// @RestController
// public class BlogRestController {
//     private final BlogService blogService;

//     @PostMapping("/api/articles")
//     public ResponseEntity<Article> addArticle(@ModelAttribute AddArticleRequest request) {
//         Article saveArticle = blogService.save(request); // 게시글 저장
//         return ResponseEntity.status(HttpStatus.CREATED) // 상태 코드 및 게시글 정보 반환
//             .body(saveArticle);
//     }

//     @GetMapping("/favicon.ico")
//     public void favicon() {
//         // 아무 작업도 하지 않음
//     }
// }