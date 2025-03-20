package com.moonya.sb2025_miniproject_jpa.domain;

import jakarta.persistence.*;
import lombok.*;

// 엔티티 클래스에 setter쓰면 죄다 db에 업데이트 날리니까 @쓰지말고 필요한거만 직접 만들자
@Entity // 아래의 클래스가 db의 테이블로 매핑
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Board extends BaseEntity {
    @Id // 아래 필드를 pk로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // unique한 값을 생성하는 방법(현재는 mysql의 AI)
    private Long bno;

    @Column(length = 13, nullable = false)
    private String writer;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(length = 2000, nullable = false)
    private String content;
    @Builder.Default
    private Long readCount = 0L;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
