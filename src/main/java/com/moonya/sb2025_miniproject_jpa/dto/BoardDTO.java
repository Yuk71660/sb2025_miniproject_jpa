package com.moonya.sb2025_miniproject_jpa.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BoardDTO {
    private Long bno;

    private String writer;
    private String title;
    private String content;

    private Long readCount;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
