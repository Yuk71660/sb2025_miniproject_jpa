package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.domain.Member;
import com.moonya.sb2025_miniproject_jpa.dto.*;
import com.moonya.sb2025_miniproject_jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@RequiredArgsConstructor
@Log4j2
@Validated
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void registerMember(MemberDTO dto) {
        Member member = Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .name(dto.getName())
                .build();

        memberRepository.save(member);
    }
}
