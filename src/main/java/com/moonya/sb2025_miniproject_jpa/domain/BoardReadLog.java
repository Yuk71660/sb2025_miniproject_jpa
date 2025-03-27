package com.moonya.sb2025_miniproject_jpa.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.Instant;
import java.time.Duration;

// 엔티티 클래스에 setter쓰면 죄다 db에 업데이트 날리니까 @쓰지말고 필요한거만 직접 만들자
@Entity // 아래의 클래스가 db의 테이블로 매핑
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "board")
public class BoardReadLog extends BaseEntity {

    @Id // 아래 필드를 pk로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // unique한 값을 생성하는 방법(현재는 mysql의 AI)
    private Long readLogNo;

    private String ipAddr;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public boolean checkOneDay() {
        Instant before = this.getModDate().toInstant(ZoneOffset.UTC);
        Instant now = Instant.now();

        Duration duration = Duration.between(before, now);
        return duration.toHours() < 24;
    }

    public void setModDate() {
        super.setModDate(LocalDateTime.now());
    }

}
