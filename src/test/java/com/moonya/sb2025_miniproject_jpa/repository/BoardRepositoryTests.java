package com.moonya.sb2025_miniproject_jpa.repository;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import com.moonya.sb2025_miniproject_jpa.dto.BoardReplyCountDTO;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository; // 테스트 유닛 에서는 보통 테스트 대상을 주입 받는다...

//    @Autowired
//    private EntityManager em;

    // @Test
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

        // if(result.isPresent()){
        // log.info("select 결과 : " + result.get());
        // } else {
        // log.info("게시글없음!");
        // }

        Board board = result.orElseThrow();

        log.info("select 결과 : " + board);

    }

    @Test
    public void testSelectList() {

        List<Board> boardList = boardRepository.findAll(Sort.by("bno").descending());

        // for (Board b : boardList){
        // log.info(b.toString());
        // }

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
        if (result.isPresent()) {
            boardRepository.deleteById(3L);
        }
    }

//     @Test
    public void insertDummies() {

        IntStream.range(1, 101).forEach(i -> {
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

    @Test
    public void testGetBoard() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Board> result = boardRepository.getBoardByWriterKeyword("er9", pageable);
        result.getContent().forEach(board -> log.info(board));
    }

    @Test
    @Transactional // 이 어노테이션을 사용하지 않으면 트랜잭션 리콰이어드 익셉션 발생
    @Commit // test모듈에서는 자동롤백있어서 커밋하려면 이거 넣어야함
    public void testUpdateBoardTitleAndContent() {
        int result = boardRepository.updateBoardTitleAndContent("updateTest", "updateTestttttt", 6L);
        log.info(result);
    }

    @Test
    public void testUpdateBoard() {
        Long bno = 6L;
        String title = "수정테스트", content = "테스트중";
        Optional<Board> result = boardRepository.findById(bno);
        if (result.isPresent()) {
            Board board = result.get();
            board.setTitle(title);
            board.setContent(content);
            boardRepository.save(board);
        }

    }

    @Test
    public void testSearchBoardTitle3() {
        Page<Board> result = boardRepository.searchBoardTitle3(PageRequest.of(0,3));

        result.getContent().forEach(board -> log.info(board));
    }

    @Test
    public void testSearchAll() {
        String[] searchTypes = {"t", "w", "c"};
        String keyword = "e";
        Page<Board> result = boardRepository.searchAll(searchTypes, keyword, PageRequest.of(0,3));

        result.getContent().forEach(board -> log.info(board));
    }

    @Test
    public void testSear() {
        String[] searchTypes = {"t"};
        String keyword = "e";
        Page<BoardReplyCountDTO> result = boardRepository.searchWithReplyCount(searchTypes, keyword,
                PageRequest.of(0,10, Sort.by("bno").descending()));

        result.getContent().forEach(board -> log.info(board));
    }
}
