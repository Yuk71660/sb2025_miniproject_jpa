package com.moonya.sb2025_miniproject_jpa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 불필요한 select 쿼리문 줄이기위해 exclude를 사용
@ToString(exclude = "board")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    // 연관관계의 주인은 변화가 많은쪽 (fk를 기준으로 설정)
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String replyer;

    private String replyText;

    public void setReplyText(String newReplyTest) {
        this.replyText = newReplyTest;
    }
}
