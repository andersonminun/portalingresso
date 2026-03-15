package com.portalingresso.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public record ErrorResponse(LocalDateTime timestamp, Integer status, String error, List<String> messages) {}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), 
            "Erro de validação nos campos enviados");
        problemDetail.setTitle("Erro de Validação");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("messages", errors);

        return ResponseEntity.status(400).body(problemDetail);
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<ProblemDetail> handleDomainExceptions(RuntimeException ex) {
        
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(422), ex.getMessage());
        problemDetail.setTitle("Violação de Regra de Negócio");
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return ResponseEntity.status(422).body(problemDetail);
    }
}
