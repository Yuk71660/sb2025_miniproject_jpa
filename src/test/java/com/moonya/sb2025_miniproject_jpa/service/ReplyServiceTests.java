package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.dto.PageRequestDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageResponseDTO;
import com.moonya.sb2025_miniproject_jpa.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    @Autowired
    private ReplyService replyService;

    @Test
    public void testRegistReply() {
        ReplyDTO replyDTO = ReplyDTO.builder()
                .bno(99L)
                .replyText("댓글 등록 테스트")
                .replyer("tester")
                .build();

        log.info(replyService.registReply(replyDTO));
    }

    @Test
    public void testGetReplies() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<ReplyDTO> pageResponseDTO = replyService.getReplies(100L, pageRequestDTO);
        pageResponseDTO.getDtoList().forEach(boardDTO -> log.info(boardDTO));
    }
}
