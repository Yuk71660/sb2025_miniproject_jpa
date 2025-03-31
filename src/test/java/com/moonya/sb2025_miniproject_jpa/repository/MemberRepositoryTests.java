package com.moonya.sb2025_miniproject_jpa.repository;

import com.moonya.sb2025_miniproject_jpa.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test // TestCase_TC001
    public void testInsert_A() {
        Member member = Member.builder()
                .id("dbrrmsdn14")
                .password("dbrrmsdn**19")
                .email("dbrrmsdn51@naver.com")
                .name("육근우")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC002
    public void testInsert_B() {
        Member member = Member.builder()
                .id("dbrrmsdn14")
                .password("asdfqrwr@@13")
                .email("doodo14@naver.com")
                .name("둘리")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC003
    public void testInsert_C() {
        Member member = Member.builder()
                .id("sdt")
                .password("asdg$5aftw")
                .email("douta13@naver.com")
                .name("도우너")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC003_re
    public void testInsert_Ca() {
        Member member = Member.builder()
                .id("taw")
                .password("tewasdfew@a15")
                .email("tewadf13@naver.com")
                .name("깐따삐아")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC003_re_re
    public void testInsert_Cb() {
        Member member = Member.builder()
                .id("taw")
                .password("tewasdfew@a15")
                .email("tewadf13@naver.com")
                .name("깐따삐아")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC001_re
    public void testInsert_Aa() {
        Member member = Member.builder()
                .id("tawgdas")
                .password("tewasdfew@a15")
                .email("tewadf13@naver.com")
                .name("깐따삐아")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC004
    public void testInsert_D() {
        Member member = Member.builder()
                .id("sdtadgwfadftwagf")
                .password("tewtst123@")
                .email("tester52@naver.com")
                .name("테스터")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC005
    public void testInsert_E() {
        Member member = Member.builder()
                .id("tsdfewr")
                .password("durtk#1")
                .email("tesmu13@naver.com")
                .name("테스트")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC006
    public void testInsert_F() {
        Member member = Member.builder()
                .id("tsdfewr")
                .password("durtkdurtka1")
                .email("tesmu13@naver.com")
                .name("테스트")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_1
    public void testInsert_Ga() {
        Member member = Member.builder()
                .id("")
                .password("dbrrmsdn**19")
                .email("dbrrmsdn51@naver.com")
                .name("육근우")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_2
    public void testInsert_Gb() {
        Member member = Member.builder()
                .id("dbrrmsdn")
                .password("")
                .email("dbrrmsdn51@naver.com")
                .name("육근우")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_3
    public void testInsert_Gc() {
        Member member = Member.builder()
                .id("dbrrmsdn")
                .password("dbrrmsdn**19")
                .email("")
                .name("육근우")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_4
    public void testInsert_Gd() {
        Member member = Member.builder()
                .id("dbrrmsdn")
                .password("dbrrmsdn**19")
                .email("dbrrmsdn51@naver.com")
                .name("")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_5
    public void testInsert_Ge() {
        Member member = Member.builder()
                .password("dbrrmsdn**19")
                .email("dbrrmsdn51@naver.com")
                .name("육근우")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_6
    public void testInsert_Gf() {
        Member member = Member.builder()
                .id("dbrrmsdn")
                .email("dbrrmsdn51@naver.com")
                .name("육근우")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_7
    public void testInsert_Gg() {
        Member member = Member.builder()
                .id("dbrrmsdn")
                .password("dbrrmsdn**19")
                .name("육근우")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_8
    public void testInsert_Gh() {
        Member member = Member.builder()
                .id("dbrrmsdn")
                .password("dbrrmsdn**19")
                .email("dbrrmsdn51@naver.com")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_1_re
    public void testInsert_Gaa() {
        Member member = Member.builder()
                .id("")
                .password("dbrrmsdn**19")
                .email("dbrrmsdn51@naver.com")
                .name("육근우")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_2_re
    public void testInsert_Gba() {
        Member member = Member.builder()
                .id("dbrrmsdn")
                .password("")
                .email("dbrrmsdn51@naver.com")
                .name("육근우")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_3_re
    public void testInsert_Gca() {
        Member member = Member.builder()
                .id("dbrrmsdn")
                .password("dbrrmsdn**19")
                .email("")
                .name("육근우")
                .build();
        memberRepository.save(member);
    }
    @Test // TestCase_TC007_4_re
    public void testInsert_Gda() {
        Member member = Member.builder()
                .id("dbrrmsdn")
                .password("dbrrmsdn**19")
                .email("dbrrmsdn51@naver.com")
                .name("")
                .build();
        memberRepository.save(member);
    }

}
