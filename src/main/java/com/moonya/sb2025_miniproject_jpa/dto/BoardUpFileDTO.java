package com.moonya.sb2025_miniproject_jpa.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardUpFileDTO {
    private String uuid;
    private String originalFileName;
    private int ord;

    private boolean img;
}
