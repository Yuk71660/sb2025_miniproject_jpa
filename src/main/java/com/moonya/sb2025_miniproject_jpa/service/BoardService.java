package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.dto.BoardDTO;
import com.moonya.sb2025_miniproject_jpa.dto.BoardReplyCountDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageRequestDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageResponseDTO;

public interface BoardService {
    Long registerBoard(BoardDTO boardDTO);

    BoardDTO readOne(Long bno, String addr);

    void modifyBoard(BoardDTO boardDTO);

    void removeBoard(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
