package com.moonya.sb2025_miniproject_jpa.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MemberDTO {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 5, max = 15, message = "아이디는 5자 이상 15자 이하이어야 합니다.")
    private String id;


    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*]).+$",
            message = "비밀번호는 숫자와 특수문자를 포함해야 합니다."
    )
    private String password;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
}
