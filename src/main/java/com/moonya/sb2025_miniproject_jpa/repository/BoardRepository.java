package com.moonya.sb2025_miniproject_jpa.repository;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
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
    List<Board> getBoardLatest5 ();
}
