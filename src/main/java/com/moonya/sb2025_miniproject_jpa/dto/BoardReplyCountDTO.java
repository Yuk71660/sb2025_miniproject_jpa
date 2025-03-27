package com.moonya.sb2025_miniproject_jpa.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardReplyCountDTO {
    private Long bno;
    private String title;
    private String writer;

    private Long readCount;
    private LocalDateTime regDate;

    private Long replyCount;
}
