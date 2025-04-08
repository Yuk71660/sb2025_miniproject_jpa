package com.moonya.sb2025_miniproject_jpa.repository.search;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import com.moonya.sb2025_miniproject_jpa.dto.BoardReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> searchBoardTitle3(Pageable pageable);

    Page<Board> searchAll(String[]searchTypes, String keyword, Pageable pageable);

    Page<BoardReplyCountDTO> searchWithReplyCount(String[]searchTypes, String keyword, Pageable pageable);

    Page<BoardReplyCountDTO> searchWithAll(String[] searchTypes, String keyword, Pageable pageable);
}
