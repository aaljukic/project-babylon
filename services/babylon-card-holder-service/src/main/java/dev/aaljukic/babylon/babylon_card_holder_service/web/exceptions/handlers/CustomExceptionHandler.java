package dev.aaljukic.babylon.babylon_card_holder_service.web.exceptions.handlers;

import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dto.ApiCustomResponse;
import dev.aaljukic.babylon.babylon_card_holder_service.web.exceptions.CardHolderConflictException;
import dev.aaljukic.babylon.babylon_card_holder_service.web.exceptions.CardHolderNotFoundException;
import dev.aaljukic.babylon.babylon_card_holder_service.web.exceptions.StatusDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiCustomResponse<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        ApiCustomResponse<Object> apiCustomResponse = ApiCustomResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .message("Invalid input format - " + ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(apiCustomResponse);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<?> handleBadRequestExceptions(Exception ex) {
        BindingResult bindingResult;

        if (ex instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
        } else {
            bindingResult = ((BindException) ex).getBindingResult();
        }

        Map<String, List<String>> errors = new HashMap<>();
        List<String> errorMessages =  bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .toList();

        errors.put("errors", errorMessages);

        ApiCustomResponse<Object> apiCustomResponse = ApiCustomResponse.builder()
                .data(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase() + " - " + "Validation failed")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(apiCustomResponse);
    }

    @ExceptionHandler({CardHolderNotFoundException.class})
    public ResponseEntity<?> handleNotFoundExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        ApiCustomResponse<Object> apiCustomResponse = ApiCustomResponse.builder()
                .data(error)
                .status(HttpStatus.NOT_FOUND.name())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase() + " - " + ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiCustomResponse);
    }

    @ExceptionHandler(CardHolderConflictException.class)
    public ResponseEntity<?> handleUserConflictException(CardHolderConflictException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        ApiCustomResponse<Object> apiCustomResponse = ApiCustomResponse.builder()
                .data(error)
                .status(HttpStatus.CONFLICT.name())
                .message(HttpStatus.CONFLICT.getReasonPhrase() + " - " + ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiCustomResponse);
    }

    @ExceptionHandler(StatusDoesntExistException.class)
    public ResponseEntity<?> handleStatusDoesntExistException(StatusDoesntExistException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        ApiCustomResponse<Object> apiCustomResponse = ApiCustomResponse.builder()
                .data(error)
                .status(HttpStatus.BAD_REQUEST.name())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase() + " - " + ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiCustomResponse);
    }
}
