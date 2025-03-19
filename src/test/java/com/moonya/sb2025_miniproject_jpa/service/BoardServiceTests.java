package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.dto.BoardDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageRequestDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageResponseDTO;
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

    @Test
    public void testReadOne() {
        long bno = 6;
        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO);
    }

    @Test
    public void testModifyBoard() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(6L)
                .title("수정")
                .content("수정중...")
                .build();
        boardService.modifyBoard(boardDTO);
    }

    @Test
    public void testRemoveBoard() {
        long bno = 6;
        boardService.removeBoard(bno);
    }

    @Test
    public void testStringToStringArray() {
        String searchType = "tw";


        char[] charArray = searchType.toCharArray();
        String[] searchTypes = new String[charArray.length];

//       String[] searchTypes = searchType.split("");

        for (int i = 0; i < charArray.length; i++) {
            searchTypes[i] = String.valueOf(charArray[i]);
        }

        log.info(searchTypes);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .searchType("t")
                .keyword("3")
                .build();
        PageResponseDTO pageResponseDTO = boardService.list(pageRequestDTO);
        pageResponseDTO.getDtoList().forEach(boardDTO -> log.info(boardDTO));
    }
}
