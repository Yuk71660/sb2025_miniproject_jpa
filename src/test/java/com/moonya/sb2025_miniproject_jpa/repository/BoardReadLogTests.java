package com.moonya.sb2025_miniproject_jpa.repository;

import com.moonya.sb2025_miniproject_jpa.domain.BoardReadLog;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class BoardReadLogTests {
    @Autowired
    private BoardReadLogRepository boardReadLogRepository;
    @Test
    public void TestFindByBnoAndIpAddr() {
        Optional<BoardReadLog> result = null;
        boardReadLogRepository.findByBnoAndIpAddr(206l, "0:0:0:0:0:0:1");

        log.info(result);
    }
}
