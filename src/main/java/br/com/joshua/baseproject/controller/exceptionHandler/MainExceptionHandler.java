package br.com.joshua.baseproject.controller.exceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainExceptionHandler {

    public record ErrorResponse(int status, String path, String message) {}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(HttpServletRequest req, Exception e) {
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse(500, req.getServletPath(), e.getMessage()));
    }
}
