package com.moonya.sb2025_miniproject_jpa.repository;

import com.moonya.sb2025_miniproject_jpa.domain.Board;

import com.moonya.sb2025_miniproject_jpa.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    // JpaRepository 에 선언된 메서드들 상속 받아서 여쯤 어디에 있는 느낌

    // JPA 에서는 필요 하다면... 쿼리문 이용 가능

    // 쿼리 메서드(Named Query) 이용_제목검색
    List<Board> findByTitleLike(String pattern, Pageable pageable);

    // 글번호 ?이하
    List<Board> findByBnoLessThanEqual(Long bno);

    // 최신 5개 가져오기
    List<Board> findTop5ByOrderByBnoDesc();

    // @Query 어노테이션으로 JPQL이나 네이티브 쿼리 사용
    @Query(nativeQuery = true, value = "select * from board order by bno desc limit 5")
    List<Board> getBoardLatest5();

    // JPQL ( Java Persistence Query Language )
    @Query(value = "select b from Board b where b.writer like concat('%', :keyword, '%')")
    Page<Board> getBoardByWriterKeyword(String keyword, Pageable pageable);

    // 타이틀 컨텐츠 업데이트
    // 업뎃은 find로 객체들고와서 세터날리고 세이브하는게 알기쉽고 편하다
    @Modifying
    @Query(value = "update Board b set b.title = :title, b.content = :content where b.bno = :bno")
    int updateBoardTitleAndContent(@Param("title") String title, @Param("content") String content, @Param("bno") Long bno);

    // 3. 동적 쿼리문 작성할 때, 위에 쿼리문 쓰는 것들은 써먹기도 힘들고 오타찾기도 힘들어서 쿼리dsl쓴다

}
