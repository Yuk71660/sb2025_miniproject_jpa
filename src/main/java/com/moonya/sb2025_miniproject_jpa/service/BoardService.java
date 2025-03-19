package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.dto.BoardDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageRequestDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageResponseDTO;

public interface BoardService {
    Long registerBoard(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modifyBoard(BoardDTO boardDTO);

    void removeBoard(Long bno);

    PageResponseDTO list(PageRequestDTO pageRequestDTO);
}
