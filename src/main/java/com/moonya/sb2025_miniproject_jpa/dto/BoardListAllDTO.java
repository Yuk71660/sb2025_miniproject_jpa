package com.moonya.sb2025_miniproject_jpa.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardListAllDTO {
    private Long bno;
    private String title;
    private String writer;

    private Long readCount;
    private LocalDateTime regDate;

    private Long replyCount;

    private List<BoardUpFileDTO> boardUpFileDTOList;
}
