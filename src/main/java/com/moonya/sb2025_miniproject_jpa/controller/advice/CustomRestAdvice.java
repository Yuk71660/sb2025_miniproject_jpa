package com.moonya.sb2025_miniproject_jpa.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // view 객체 반환 안하는 REST 컨트롤러의 공통 예외 처리
@Log4j2
public class CustomRestAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 응답 상태 (HttpStatus에 대한 내용)
    public ResponseEntity<Map<String,String>> handlerBindingException(BindException e) {

        log.error(e);

        Map<String,String> errorMap = new HashMap<>();
        if (e.getBindingResult().hasErrors()){
            BindingResult bindingResult = e.getBindingResult();

            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
        }

        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String,String>> handlerSqlIntegrityException(Exception e) {
        log.error(e);

        Map<String,String> errorMap = new HashMap<>();
        if (e instanceof DataIntegrityViolationException){
            errorMap.put("messege", "존재하는 글에 대해 댓글을 작성해주세오");
            errorMap.put("error", e.getMessage());
        } else {
            errorMap.put("error", e.getMessage());
        }

        return ResponseEntity.badRequest().body(errorMap);
    }
}
