package com.moonya.sb2025_miniproject_jpa.controller;

import com.moonya.sb2025_miniproject_jpa.dto.BoardDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageRequestDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageResponseDTO;
import com.moonya.sb2025_miniproject_jpa.service.BoardService;
import com.moonya.sb2025_miniproject_jpa.util.GetClientIPAddr;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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

    @GetMapping("/showRegisterForm")
    public String registerForm() {

        return "board/registerForm";
    }

    @PostMapping("/register")
    public String register(@Valid BoardDTO boardDTO, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("유효성 검사 실패");
            log.info(bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/showRegisterForm";
        }

        Long bno = boardService.registerBoard(boardDTO);
        redirectAttributes.addFlashAttribute("result", bno + "번 게시글 등록 완료");
        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public void readBoard(Long bno, PageRequestDTO pageRequestDTO, Model model, HttpServletRequest request) {
        log.info(bno);

        String ipAddr = GetClientIPAddr.getClientIp(request);

        model.addAttribute("dto", boardService.readOne(bno, ipAddr));
    }

}
