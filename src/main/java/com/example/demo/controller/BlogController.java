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
    

    @GetMapping("/article_list")
    public String article_list(Model model) {
        List<Article> list = blogService.findAll();
        model.addAttribute("articles", list);
        return "article_list";
    }

    @PostMapping("/api/articles")
    public String addArticle(@ModelAttribute AddArticleRequest request) {
        blogService.save(request); // 게시글 저장
        return "redirect:/article_list";
    }

    @GetMapping("/article_edit/{id}") // 게시판 링크 지정
    public String article_edit(@PathVariable String id, Model model) {

        if(!id.matches("\\d+")) {
            return "/error_page/article_error2";
        }

        Long longId = Long.parseLong(id);
        Optional<Article> list = blogService.findById(longId);

        if (list.isPresent()) {
            model.addAttribute("article", list.get());
        } else {
            // 처리할로직추가(예: 오류페이지로리다이렉트, 예외처리등)
            return "/error_page/article_error"; // 오류 처리 페이지로 이동(이름 수정됨)
        }
        return "article_edit"; //.HTML 연결
    }

    @PutMapping("/api/article_edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/article_list"; // 글 수정 이후 .html 연결
    }

    @DeleteMapping("/api/article_delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/article_list";
    }

    @GetMapping("/board_list") // 새로운 게시판 링크 지정
    public String board_list(Model model) {
    List<Board> boards = blogService.findAll(); // 게시판 전체 리스트, 기존 Article에서 Board로 변경됨
    model.addAttribute("boards", boards); // 모델에 추가
    return "board_list"; // .HTML 연결
    }

}