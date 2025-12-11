package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.model.domain.*;
import com.example.demo.model.service.*;
import java.util.Optional;
import java.lang.StackWalker.Option;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@Controller // 컨트롤러 어노테이션 명시
public class BlogController {
    
    @Autowired
    BlogService blogService;
    

    // @GetMapping("/article_list")
    // public String article_list(Model model) {
    //     List<Article> list = blogService.findAll();
    //     model.addAttribute("articles", list);
    //     return "article_list";
    // }

    @PostMapping("/api/boards")
    public String addBoard(@ModelAttribute AddBoardRequest request) {
        blogService.save(request); // 게시글 저장
        return "redirect:/board_list";
    }

    // @GetMapping("/article_edit/{id}") // 게시판 링크 지정
    // public String article_edit(@PathVariable String id, Model model) {

    //     if(!id.matches("\\d+")) {
    //         return "/error_page/article_error2";
    //     }

    //     Long longId = Long.parseLong(id);
    //     Optional<Article> list = blogService.findById(longId);

    //     if (list.isPresent()) {
    //         model.addAttribute("article", list.get());
    //     } else {
    //         // 처리할로직추가(예: 오류페이지로리다이렉트, 예외처리등)
    //         return "/error_page/article_error"; // 오류 처리 페이지로 이동(이름 수정됨)
    //     }
    //     return "article_edit"; //.HTML 연결
    // }

    // @PutMapping("/api/article_edit/{id}")
    // public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
    //     blogService.update(id, request);
    //     return "redirect:/article_list"; // 글 수정 이후 .html 연결
    // }

    @GetMapping("/board_edit/{id}")  // 글 수정 화면
    public String board_edit(Model model, @PathVariable Long id) {
        Optional<Board> board = blogService.findById(id);

        if (board.isPresent()) {
            model.addAttribute("board", board.get());  // 단수 이름 사용
            return "board_edit";                       // board_edit.html 로 이동
        } else {
            // 없는 글일 때 에러 페이지
            return "/error_page/article_error";
        }
    }

    @PutMapping("/api/board_edit/{id}")
    public String updateBoard(@PathVariable Long id, @ModelAttribute AddBoardRequest request) {
        blogService.update(id, request);
        return "redirect:/board_list"; // 글 수정 이후 .html 연결
    }

    // @DeleteMapping("/api/article_delete/{id}")
    // public String deleteArticle(@PathVariable Long id) {
    //     blogService.delete(id);
    //     return "redirect:/article_list";
    // }

    @DeleteMapping("/api/board_delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/board_list";
    }

    @GetMapping("/board_list") // 새로운 게시판 링크 지정
    public String board_list(Model model) {
        List<Board> boards = blogService.findAll(); // 게시판 전체 리스트, 기존 Article에서 Board로 변경됨
        model.addAttribute("boards", boards); // 모델에 추가
        return "board_list"; // .HTML 연결
    }

    @GetMapping("/board_view/{id}") // 게시판 링크 지정
    public String board_view(Model model, @PathVariable Long id) {
        Optional<Board> list = blogService.findById(id); // 선택한 게시판 글
        if (list.isPresent()) {
            model.addAttribute("boards", list.get()); // 존재할 경우 실제 Board 객체를 모델에 추가
        } else {
            // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
            return "/error_page/article_error"; // 오류 처리 페이지로 연결
        }
        return "board_view"; // .HTML 연결
    }
    
}