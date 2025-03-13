package com.moonya.sb2025_miniproject_jpa.repository;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository; // 테스트 유닛 에서는 보통 테스트 대상을 주입 받는다...

    //    @Test
    public void testInsert() {
        Board newBoard = Board.builder()
                .title("insert test")
                .writer("dooly")
                .content("저장이 잘 되려나?")
                .build();

        Board savedEntity = boardRepository.save(newBoard);

        log.info("저장된 보드 : " + savedEntity);

    }

    @Test
    public void testSelectOne() {

        Optional<Board> result = boardRepository.findById(5L);

//        if(result.isPresent()){
//        log.info("select 결과 : " + result.get());
//        } else {
//            log.info("게시글없음!");
//        }

        Board board = result.orElseThrow();

        log.info("select 결과 : " + board);

    }

}
