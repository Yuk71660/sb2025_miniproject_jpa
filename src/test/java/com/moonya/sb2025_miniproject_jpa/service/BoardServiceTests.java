package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegisterBoard() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("test")
                .writer("tester")
                .content("test...")
                .build();
        long i = boardService.registerBoard(boardDTO);
        log.info(i);
    }
}
