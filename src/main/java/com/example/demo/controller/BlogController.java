package com.example.demo.controller;

import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.model.domain.*;
import com.example.demo.model.service.*;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@Controller // 컨트롤러 어노테이션 명시
public class BlogController {
    
    @Autowired
    BlogService blogService;

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
    public String updateBoard(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/board_list"; // 글 수정 이후 .html 연결
    }

    @DeleteMapping("/api/board_delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/board_list";
    }

    @GetMapping("/board_list") // 새로운 게시판 링크 지정
    public String board_list(Model model, @RequestParam(defaultValue = "0") int page, 
                            @RequestParam(defaultValue = "") String keyword, HttpSession session){ // 세션 객체 전달
        String userId = (String) session.getAttribute("userId"); // 세션 아이디 존재 확인
        String email = (String) session.getAttribute("email"); // 세션에서 이메일 확인
        if (userId == null) {
            return "redirect:/member_login"; // 로그인 페이지로 리다이렉션
        }
        System.out.println("세션 userId: " + userId);  // 서버 IDE 터미널에 세션 값 출력

        PageRequest pageable = PageRequest.of(page, 3); // 한 페이지의 게시글 수
        Page<Board> list; // Page를 변환

        int pageSize = 3;

        if (keyword.isEmpty()) {
            list = blogService.findAll(pageable); // 기본 전체 출력(키워드 x)
        } else {
            list = blogService.searchByKeyword(keyword, pageable); // 키워드로 검색
        }

        int startNum = page * pageSize + 1;
        model.addAttribute("startNum", startNum);
        model.addAttribute("email", email); // 로그인 사용자(이메일)
        model.addAttribute("boards", list); // 모델에 추가
        model.addAttribute("totalPages", list.getTotalPages()); //페이지 크기
        model.addAttribute("currentPage", page); // 페이지 번호
        model.addAttribute("keyword", keyword); // 키워드
        return "board_list"; // .HTML 연결
    }

    @GetMapping("/board_view/{id}") // 게시판 링크 지정
    public String board_view(Model model, @PathVariable Long id, HttpSession session) {
        
        String email = (String) session.getAttribute("email"); // 세션에서 이메일 확인
        
        Optional<Board> list = blogService.findById(id); // 선택한 게시판 글
        if (list.isPresent()) {
            model.addAttribute("boards", list.get()); // 존재할 경우 실제 Board 객체를 모델에 추가
            model.addAttribute("email", email);
        } else {
            // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
            return "/error_page/article_error"; // 오류 처리 페이지로 연결
        }
        return "board_view"; // .HTML 연결
    }
    
    // 글쓰기 게시판
    @GetMapping("/board_write")
    public String board_write() {
        return "board_write";
    }

    @PostMapping("/api/boards") // 글쓰기 게시판 저장
    public String addboards(@ModelAttribute AddArticleRequest request, HttpSession session) {

        String email = (String) session.getAttribute("email");
        request.setUser(email); // 세션에서 가져온 이메일로 사용자 설정

        blogService.save(request);
        return "redirect:/board_list"; // .HTML 연결
    }
}