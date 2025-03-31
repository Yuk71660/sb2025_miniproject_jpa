package com.moonya.sb2025_miniproject_jpa.controller;

import com.moonya.sb2025_miniproject_jpa.dto.MemberDTO;
import com.moonya.sb2025_miniproject_jpa.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/registerMember")
    public String registerForm() {

        return "member/register";
    }

    @PostMapping("/registerMember")
    public void registMember(@Valid MemberDTO memberDTO) {
        memberService.registerMember(memberDTO);
    }
}
