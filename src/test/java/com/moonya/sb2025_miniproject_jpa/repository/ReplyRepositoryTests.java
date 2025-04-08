package com.moonya.sb2025_miniproject_jpa.repository;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import com.moonya.sb2025_miniproject_jpa.domain.Reply;
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

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsertReply() {
        Board board = boardRepository.findById(1l).get();

        Reply reply = Reply.builder()
                .board(board)
                .replyer("dooly")
                .replyText("댓글 저장 테스트")
                .build();

        replyRepository.save(reply);

    }

    @Test
    public void twoSelectWithOutJoin() {
        Optional<Board> result = boardRepository.findById(100L);
        Board board = result.orElseThrow();

        List<Reply> replyList = replyRepository.findByBoard(board);

        log.info("board : {}", board);

        replyList.forEach(reply -> log.info(reply));

    }

    @Test
    public void testReplyListOfBoard() {
        Long bno = 1L;

        Pageable pageable = PageRequest.of(0,4, Sort.by("rno").descending());

        Page<Reply> result = replyRepository.replyListOfBoard(bno, pageable);

        result.getContent().forEach(reply -> log.info(reply));

    }

    @Test
    public void testSelectByRno() {
        Optional<Reply> result = replyRepository.findById(1L);
        log.info("reply :{}", result.orElseThrow());
    }

    @Test
    public void testUpdateReply() {
        Optional<Reply> result = replyRepository.findById(1L);
        if (result.isPresent()) {
            Reply oldReply = result.get();

            oldReply.setReplyText("업뎃 테스트");
            replyRepository.save(oldReply);

            testSelectByRno();
        }
    }

    @Test
    public void testRemoveReply() {
        Optional<Reply> result = replyRepository.findById(1L);
        if (result.isPresent()) {
            replyRepository.deleteById(1L);
        }
    }
}
