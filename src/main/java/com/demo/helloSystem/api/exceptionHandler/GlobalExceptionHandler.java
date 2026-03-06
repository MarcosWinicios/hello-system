package com.demo.helloSystem.api.exceptionHandler;

import com.demo.helloSystem.domain.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handle(Exception ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("timestamp", Instant.now());
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

        if(ex instanceof BadRequestException){
            statusCode = HttpStatus.BAD_REQUEST.value();
        }

        body.put("statusCode", statusCode);
        return ResponseEntity.status(statusCode).body(body);
    }

}
