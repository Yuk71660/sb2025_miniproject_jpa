package com.moonya.sb2025_miniproject_jpa.repository;

import com.moonya.sb2025_miniproject_jpa.domain.BoardReadLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardReadLogRepository extends JpaRepository<BoardReadLog, Long> {
    @Query("select l from BoardReadLog l where l.bno=:bno and l.ipAddr=:ipAddr")
    Optional<BoardReadLog> findByBnoAndIpAddr(@Param("bno") Long bno, @Param("ipAddr") String ipAddr);
}
