package com.moonya.sb2025_miniproject_jpa.repository;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import com.moonya.sb2025_miniproject_jpa.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByBoard(Board board);

    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> replyListOfBoard(@Param("bno")Long bno, Pageable pageable);
}
