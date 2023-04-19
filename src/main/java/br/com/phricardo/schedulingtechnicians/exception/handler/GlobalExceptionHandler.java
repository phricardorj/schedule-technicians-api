package br.com.phricardo.schedulingtechnicians.exception.handler;

import br.com.phricardo.schedulingtechnicians.exception.RegistrationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ErrorResponse(NOT_FOUND.value(), ex.getLocalizedMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBadRequest(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidatedErrors::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleBadRequest(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ErrorResponse(BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials() {
        return ResponseEntity.status(UNAUTHORIZED)
                .body(new ErrorResponse(UNAUTHORIZED.value(), "Invalid credentials"));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationFailure() {
        return ResponseEntity.status(UNAUTHORIZED)
                .body(new ErrorResponse(UNAUTHORIZED.value(), "Authentication failed"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied() {
        return ResponseEntity.status(FORBIDDEN)
                .body(new ErrorResponse(FORBIDDEN.value(), "Access denied"));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElement(NoSuchElementException ex) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ErrorResponse(NOT_FOUND.value(), ex.getLocalizedMessage()));
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<?> handleRegistration(RegistrationException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ErrorResponse(BAD_REQUEST.value(), ex.getLocalizedMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInternalServerError(Exception ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage()));
    }

    private record ValidatedErrors(String field, String message) {
        public ValidatedErrors(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    public record ErrorResponse(LocalDateTime date, int statusCode, String message) {
        public ErrorResponse(int statusCode, String message) {
            this(LocalDateTime.now(), statusCode, message);
        }
    }
}
