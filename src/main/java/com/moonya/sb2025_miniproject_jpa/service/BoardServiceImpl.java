package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import com.moonya.sb2025_miniproject_jpa.dto.BoardDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageRequestDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageResponseDTO;
import com.moonya.sb2025_miniproject_jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Override
    public BoardDTO readOne(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        BoardDTO dto = modelMapper.map(board, BoardDTO.class);
        return dto;
    }

    @Override
    public void modifyBoard(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        if (result.isPresent()) {
            Board board = result.get();

            board.setTitle( boardDTO.getTitle());
            board.setContent(boardDTO.getContent());

            boardRepository.save(board);
        }
    }

    @Override
    public void removeBoard(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        if (result.isPresent()) {
            boardRepository.deleteById(bno);
        } else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public PageResponseDTO list(PageRequestDTO pageRequestDTO) {
        String[] searchTypes = pageRequestDTO.getSearchTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result = boardRepository.searchAll(searchTypes, keyword, pageable);

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .toList();


        return PageResponseDTO.withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }


}
