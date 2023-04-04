package br.com.phricardo.schedulingtechnicians.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> argsNotValid(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorsValid::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorsValid message =
                new ErrorsValid(
                "JSON deserialization error",
                "The request sent is invalid. Check that all fields are filled in correctly."
                );
        return ResponseEntity.badRequest().body(message);
    }

    private record ErrorsValid(String field, String message) {
        public ErrorsValid(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
