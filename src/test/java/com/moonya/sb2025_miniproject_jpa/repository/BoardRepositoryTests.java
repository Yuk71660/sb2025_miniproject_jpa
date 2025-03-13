package com.moonya.sb2025_miniproject_jpa.repository;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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

    @Test
    public void testSelectList() {

        List<Board> boardList = boardRepository.findAll(Sort.by("bno").descending());

//        for (Board b : boardList){
//        log.info(b.toString());
//        }

        boardList.forEach(board -> log.info(board));

    }

    @Test
    public void testUpdate() {
        Optional<Board> result = boardRepository.findById(2L);
        Board board = result.orElseThrow();
        board.setTitle("수정테스트");
        board.setContent("테스트트");

        Board oldBoard = Board.builder()
                .bno(1L)
                .title("수정")
                .writer("dooly")
                .content("저장이 잘 되려나?")
                .build();

        Board modifiedBoard = boardRepository.save(board);
        Board modifiedOldBoard = boardRepository.save(oldBoard);

        log.info(modifiedBoard);
        log.info(modifiedOldBoard);
    }

    @Test
    public void testDelete() {
        Optional<Board> result = boardRepository.findById(3L);
        if (result.isPresent()){
        boardRepository.deleteById(3L);
        }
    }

//    @Test
    public void insertDummies() {

        IntStream.range(1,101).forEach(i->{
            Board newBoard = Board.builder()
                    .title("title..." + i)
                    .writer("user" + i % 10)
                    .content("content..." + i)
                    .build();

        boardRepository.save(newBoard);

        });

    }

    @Test
    public void testPaging() {
        // 페이징 처리는 Pageable객체 파라미터로 전달
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.findAll(pageable);
        result.getContent().forEach(board -> log.info(board));

        if (result.hasNext()) {
        Page<Board> resultt = boardRepository.findAll(result.getPageable().next());
        resultt.getContent().forEach(board -> log.info(board));
        }
    }

    @Test
    public void testFindByTitleLike() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("bno").descending());
        List<Board> boardList = boardRepository.findByTitleLike("%1%", pageable);
        boardList.forEach(board -> log.info(board));
    }

    @Test
    public void testFindByBnoLessThanEqual() {
        List<Board> boardList = boardRepository.findByBnoLessThanEqual(30L);
        boardList.forEach(board -> log.info(board));
    }

    @Test
    public void testGetBoardLatest5() {
        List<Board> boardList = boardRepository.getBoardLatest5();
        boardList.forEach(board -> log.info(board));
    }

}
