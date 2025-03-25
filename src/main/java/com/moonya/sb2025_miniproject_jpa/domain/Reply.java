package com.moonya.sb2025_miniproject_jpa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    // 연관관계의 주인은 변화가 많은쪽 (fk를 기준으로 설정)
    @ManyToOne
    private Board board;

    private String replyer;

    private String replyText;
}
