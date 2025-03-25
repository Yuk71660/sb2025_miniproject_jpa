package com.moonya.sb2025_miniproject_jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Long rno;
    private Long bno;
    private String replyer;
    private String replyText;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
