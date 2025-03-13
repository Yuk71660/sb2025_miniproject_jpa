package com.moonya.sb2025_miniproject_jpa.repository;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
    // JpaRepository 에 선언된 메서드들 상속받아서 여쯤 어디에 있는느낌
}
