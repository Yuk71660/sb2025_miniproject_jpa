package com.moonya.sb2025_miniproject_jpa.controller;

import com.moonya.sb2025_miniproject_jpa.dto.ReplyDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {

    @Operation(summary = "Replies POST", description = "POST방식으로 댓글 등록", method = "POST")
    @PostMapping(value = "/" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> register(ReplyDTO replyDTO) {
        log.info("reply : {}", replyDTO);
        return null;
    }

}
