package com.moonya.sb2025_miniproject_jpa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "댓글을 입력할 게시글 번호는 반드시 있어야 합니다.")
    private Long bno;
    @NotEmpty(message = "작성자를 입력하세요")
    private String replyer;
    @NotEmpty(message = "댓글 내용을 입력하세요")
    private String replyText;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    @JsonIgnore
    private LocalDateTime modDate;
}
