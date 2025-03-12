package com.moonya.sb2025_miniproject_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // AuditingEntityListener 리스너 서버 실행 시 자동 등록
public class Sb2025MiniprojectJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb2025MiniprojectJpaApplication.class, args);
	}

}
