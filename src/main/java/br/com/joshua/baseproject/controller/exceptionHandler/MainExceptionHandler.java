package br.com.joshua.baseproject.controller.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class MainExceptionHandler {

    public record ErrorResponse(int status, String path, String message) {}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(HttpServletRequest req, Exception e) {
    	e.printStackTrace();
    	log.info(e.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse(500, req.getServletPath(), e.getMessage()));
    }
}
