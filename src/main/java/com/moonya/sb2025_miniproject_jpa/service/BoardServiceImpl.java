package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import com.moonya.sb2025_miniproject_jpa.domain.BoardReadLog;
import com.moonya.sb2025_miniproject_jpa.dto.*;
import com.moonya.sb2025_miniproject_jpa.repository.BoardReadLogRepository;
import com.moonya.sb2025_miniproject_jpa.repository.BoardRepository;
import com.moonya.sb2025_miniproject_jpa.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;
    private final BoardReadLogRepository boardReadLogRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Long registerBoard(BoardDTO boardDTO) {
        boardDTO.setReadCount(0L);
        Board board = dtoToEntity(boardDTO);

        log.info("board : {}", board);
        boardRepository.save(board);

        return board.getBno();
    }

    @Override
    public BoardReadDTO readOne(Long bno, String ipAddr) {

        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();

        readCountProcess(bno, ipAddr, board);

        BoardReadDTO dto = entityToDTO(board);

        return dto;
    }

    private void readCountProcess(Long bno, String ipAddr, Board board) {
        Optional<BoardReadLog> result = boardReadLogRepository.findByBnoAndIpAddr(bno, ipAddr);
        BoardReadLog beforeReadLog = null;
        if (result.isPresent()){
            beforeReadLog = result.get();

            if (beforeReadLog.checkOneDay()) {
                board.setReadCount();
                beforeReadLog.setModDate();
                boardReadLogRepository.save(beforeReadLog);
            }

        } else {
            board.setReadCount();
            BoardReadLog newReadLog = BoardReadLog.builder()
                    .ipAddr(ipAddr)
                    .board(Board.builder().bno(bno).build())
                    .build();
            boardReadLogRepository.save(newReadLog);
        }
    }

    @Override
    public void modifyBoard(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        if (result.isPresent()) {
            Board board = result.get();

            board.clearAllFiles();

            board.setTitle( boardDTO.getTitle());
            board.setContent(boardDTO.getContent());

            if (boardDTO.getFileNames() != null) {
                for(String fileName : boardDTO.getFileNames()) {
                    // uuid(36자리) + _ + 원본이름
                    String uuid = fileName.substring(0,36) ;
                    String originalFileName = fileName.substring(37);
                    String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                    Boolean img = false;
                    if (ext != null && (
                            ext.equalsIgnoreCase("jpg") ||
                                    ext.equalsIgnoreCase("jpeg") ||
                                    ext.equalsIgnoreCase("png") ||
                                    ext.equalsIgnoreCase("gif") ||
                                    ext.equalsIgnoreCase("bmp") ||
                                    ext.equalsIgnoreCase("webp") ||
                                    ext.equalsIgnoreCase("tiff") ||
                                    ext.equalsIgnoreCase("svg")
                    )) {
                        img = true;
                    }
                    board.addUpFile(uuid, originalFileName, img);
                }
            }

            boardRepository.save(board);
        }
    }

    @Override
    @Transactional
    public void removeBoard(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        if (result.isPresent()) {
            boardReadLogRepository.deleteByBoard_Bno(bno);
            replyRepository.deleteByBoard_Bno(bno);
            boardRepository.deleteById(bno);
        } else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {

        String[] searchTypes = pageRequestDTO.getSearchTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result = boardRepository.searchAll(searchTypes, keyword, pageable);

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .toList();


        return new PageResponseDTO<BoardDTO>(pageRequestDTO, dtoList, (int)result.getTotalElements(),
                pageRequestDTO.getSearchType(), pageRequestDTO.getKeyword(), pageRequestDTO.getLink());
    }

    @Override
    public PageResponseDTO<BoardReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO) {

        String[] searchTypes = pageRequestDTO.getSearchTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<BoardReplyCountDTO> result = boardRepository.searchWithReplyCount(searchTypes, keyword, pageable);

        List<BoardReplyCountDTO> dtoList = result.getContent();


        return new PageResponseDTO<BoardReplyCountDTO>(pageRequestDTO, dtoList, (int)result.getTotalElements(),
                pageRequestDTO.getSearchType(), pageRequestDTO.getKeyword(), pageRequestDTO.getLink());
    }

    @Override
    public PageResponseDTO<BoardListAllDTO> listAll(PageRequestDTO pageRequestDTO) {
        String[] searchTypes = pageRequestDTO.getSearchTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<BoardListAllDTO> result = boardRepository.searchWithAll(searchTypes, keyword, pageable);

        PageResponseDTO<BoardListAllDTO> res = new PageResponseDTO<>(
                pageRequestDTO,
                result.getContent(),
                (int)result.getTotalElements(),
                pageRequestDTO.getSearchType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getLink()
                );
        return res;
    }


}
