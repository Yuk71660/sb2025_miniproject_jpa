package com.moonya.sb2025_miniproject_jpa.controller;

import com.moonya.sb2025_miniproject_jpa.dto.ReplyDTO;
import com.moonya.sb2025_miniproject_jpa.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Operation(summary = "Replies POST", description = "POST방식으로 댓글 등록", method = "POST")
    @PostMapping(value = "/" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException {
        log.info("reply : {}", replyDTO);

        if (bindingResult.hasErrors()) {
            log.info("Error");
            throw new BindException(bindingResult);
        }

        Long rno = replyService.registReply(replyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("newRno", rno);

        return ResponseEntity.ok().body(resultMap);
    }

}
