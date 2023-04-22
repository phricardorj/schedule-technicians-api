package br.com.phricardo.schedulingtechnicians.exception.handler;

import br.com.phricardo.schedulingtechnicians.exception.RegistrationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
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
import java.util.List;
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
        final var errors = ex.getFieldErrors().stream().map(ValidatedErrors::new).toList();
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ErrorResponse(BAD_REQUEST.value(), errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleBadRequest(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ErrorResponse(BAD_REQUEST.value(), "Invalid request payload: please check that your request payload is a valid JSON object and that all required fields are present."));
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        var message = ex.getLocalizedMessage();
        final var constraintName = ex.getMostSpecificCause().getMessage();
        if (constraintName.startsWith("Duplicate entry")) {
            String[] parts = constraintName.split("'");
            final var value = parts[1];
            final var column = parts[3].substring(parts[3].lastIndexOf(".") + 1);
            message = String.format("The %s '%s' is already registered", column, value);
        }
        return ResponseEntity.status(CONFLICT).body(new ErrorResponse(CONFLICT.value(), message));
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

    public record ErrorResponse(LocalDateTime date, int httpStatusCode, Object message) {
        public ErrorResponse(int statusCode, String message) {
            this(LocalDateTime.now(), statusCode, message);
        }

        public ErrorResponse(int statusCode, List<?> messageList) {
            this(LocalDateTime.now(), statusCode, messageList);
        }
    }

}
