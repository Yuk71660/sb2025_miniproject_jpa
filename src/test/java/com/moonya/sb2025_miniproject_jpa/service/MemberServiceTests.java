package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Log4j2
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;

    @Test // TestCase_TC008
    public void testRegistMember_Success() {
        MemberDTO memberDTO = MemberDTO.builder()
                .id("dnrweasfd")
                .password("durtka**19")
                .email("gwadf89@gmail.com")
                .name("박밝게빛나리")
                .build();

        memberService.registerMember(memberDTO);
    }
    @Test // TestCase_TC009
    public void testRegistMember_noPwdSpecialChar() {
        MemberDTO memberDTO = MemberDTO.builder()
                .id("tesafsd25")
                .password("durtka2619")
                .email("gwadf89@gmail.com")
                .name("박밝게빛나리")
                .build();

        memberService.registerMember(memberDTO);
    }
    @Test // TestCase_TC009_re
    public void testRegistMember_noPwdSpecialChar_re() {
        MemberDTO memberDTO = MemberDTO.builder()
                .id("tesafsd25")
                .password("durtka2619")
                .email("gwadf89@gmail.com")
                .name("박밝게빛나리")
                .build();

        assertThrows(Exception.class, () -> {
            memberService.registerMember(memberDTO);
        });
    }
    @Test // TestCase_TC010
    public void testRegistMember_noPwdNumber() {
        MemberDTO memberDTO = MemberDTO.builder()
                .id("tesafsd25")
                .password("durtka**sdfw")
                .email("gwadf89@gmail.com")
                .name("박밝게빛나리")
                .build();

        assertThrows(Exception.class, () -> {
            memberService.registerMember(memberDTO);
        });
    }
    @Test // TestCase_TC011
    public void testRegistMember_notEmail() {
        MemberDTO memberDTO = MemberDTO.builder()
                .id("tesafsd25")
                .password("durtka**sdfw15")
                .email("gwadf89gmail.com")
                .name("박밝게빛나리")
                .build();

        assertThrows(Exception.class, () -> {
            memberService.registerMember(memberDTO);
        });
    }

}
