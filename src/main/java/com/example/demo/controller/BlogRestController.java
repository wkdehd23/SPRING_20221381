// package com.example.demo.controller;

// //import com.example.demo.model.domain.Article;
// import com.example.demo.model.domain.Board;
// import com.example.demo.model.service.AddBoardRequest;
// import com.example.demo.model.service.BlogService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RequiredArgsConstructor
// @RestController
// public class BlogRestController {
//     private final BlogService blogService;

//     @PostMapping("/api/boards")
//     public ResponseEntity<Board> addBoard(@ModelAttribute AddBoardRequest request) {
//         Board saveBoard = blogService.save(request); // 게시글 저장
//         return ResponseEntity.status(HttpStatus.CREATED) // 상태 코드 및 게시글 정보 반환
//             .body(saveBoard);
//     }

//     @GetMapping("/favicon.ico")
//     public void favicon() {
//         // 아무 작업도 하지 않음
//     }
// }