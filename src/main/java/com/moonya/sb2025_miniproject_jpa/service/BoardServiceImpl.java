package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import com.moonya.sb2025_miniproject_jpa.dto.BoardDTO;
import com.moonya.sb2025_miniproject_jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Long registerBoard(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO, Board.class);
        log.info("board : {}", board);
        boardRepository.save(board);

        return board.getBno();
    }
}
