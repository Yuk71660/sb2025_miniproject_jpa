package com.moonya.sb2025_miniproject_jpa.controller;

import com.moonya.sb2025_miniproject_jpa.dto.PageRequestDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageResponseDTO;
import com.moonya.sb2025_miniproject_jpa.service.BoardService;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(Model model, PageRequestDTO pageRequestDTO) {
        model.addAttribute("pageResponseDTO", boardService.list(pageRequestDTO));
    }

    @GetMapping("/registerForm")
    public String registerForm() {

        return "board/registerForm";
    }

    @PostMapping("/register")
    public String register(BoardDTO boardDTO) {
        boardService.register(boardDTO);
        return "redirect:/board/list";
    }

}
