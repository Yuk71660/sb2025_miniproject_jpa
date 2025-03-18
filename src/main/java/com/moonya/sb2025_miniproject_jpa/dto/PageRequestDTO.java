package com.moonya.sb2025_miniproject_jpa.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageRequestDTO {
    @Builder.Default
    private int page = 1, size = 10;

    private String searchType, keyword;
}
