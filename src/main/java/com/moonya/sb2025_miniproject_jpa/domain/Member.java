package com.moonya.sb2025_miniproject_jpa.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.aspectj.bridge.IMessage;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member {
    @Id // 아래 필드를 pk로 지정
    @Column(length = 15)
    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Size(min = 4, message = "아이디는 최소 4자 이상이어야 합니다.")
    @Size(max = 15, message = "아이디는 최대 15자 까지만 허용됩니다.")
    private String id;
    @Column(length = 13, nullable = false)
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;
    @Column(nullable = false)
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;
}
