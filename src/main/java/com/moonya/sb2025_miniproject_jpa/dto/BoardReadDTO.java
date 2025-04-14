package com.moonya.sb2025_miniproject_jpa.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BoardReadDTO {
    private Long bno;

    @NotEmpty
    @Size(min = 2, max = 100, message = "작성자는 2자 이상 100자 이하로 입력해주세요.")
    private String title;

    @NotEmpty(message = "작성자는 필수 입력 사항입니다.")
    private String writer;

    @NotEmpty(message = "내용은 필수 입력 사항입니다.")
    private String content;

    private Long readCount;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private List<BoardUpFileDTO> fileNames;
}
