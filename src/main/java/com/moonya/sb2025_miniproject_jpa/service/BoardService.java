package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import com.moonya.sb2025_miniproject_jpa.domain.BoardUpFile;
import com.moonya.sb2025_miniproject_jpa.dto.BoardDTO;
import com.moonya.sb2025_miniproject_jpa.dto.BoardReplyCountDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageRequestDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageResponseDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface BoardService {
    Long registerBoard(BoardDTO boardDTO);

    BoardDTO readOne(Long bno, String addr);

    void modifyBoard(BoardDTO boardDTO);

    void removeBoard(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

    // default 메서드 : 인터페이스에 구현부가 있는 메서드를 작성 가능
    default Board dtoToEntity(BoardDTO b) {

        Board board = Board.builder()
                .title(b.getTitle())
                .writer(b.getWriter())
                .content(b.getContent().replace("\n","<br>"))
                .readCount(b.getReadCount())
                .build();

        if(b.getFileNames() != null) {
            b.getFileNames().forEach(fileName -> {
                // uuid(36자리) + _ + 원본이름
                String uuid = fileName.substring(0,36) ;
                String originalFileName = fileName.substring(37);
                board.addUpFile(uuid, originalFileName);
            });
        }

        return board;
    }

//    @NotEmpty
//    @Size(min = 2, max = 100, message = "작성자는 2자 이상 100자 이하로 입력해주세요.")
//    private String title;
//
//    @NotEmpty(message = "작성자는 필수 입력 사항입니다.")
//    private String writer;
//
//    @NotEmpty(message = "내용은 필수 입력 사항입니다.")
//    private String content;
//
//    private Long readCount;
//
//    private LocalDateTime regDate;
//
//    private LocalDateTime modDate;
//
//    private List<String> fileNames;
}
