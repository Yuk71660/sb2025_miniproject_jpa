package com.moonya.sb2025_miniproject_jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 다른 엔티티클래스의 부모로 사용할 클래스임을 명시
@Getter
@EntityListeners(AuditingEntityListener.class) // 엔티티 클래스의 값이 변화되었는지의 여부를 감지
abstract public class BaseEntity {
    @CreatedDate // 데이터가 생성될 시간이 자동으로 기록
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;

}
