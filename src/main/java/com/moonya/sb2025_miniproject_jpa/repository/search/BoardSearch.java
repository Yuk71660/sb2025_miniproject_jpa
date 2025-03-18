package com.moonya.sb2025_miniproject_jpa.repository.search;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> searchBoardTitle3(Pageable pageable);

    Page<Board> searchAll(String[]searchTypes, String keyword, Pageable pageable);
}
