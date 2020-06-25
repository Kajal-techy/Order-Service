package com.orderService.OrderService.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class OrderServiceExceptionHandler {

    @ExceptionHandler(value = InvalidType.class)
    public ResponseEntity<InvalidType> invalidException(InvalidType exception) {
        log.info("Entering OrderServiceExceptionHandler.invalidException with parameter exception {}.", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<NotFoundException> notFoundException(NotFoundException exception) {
        log.info("Entering OrderServiceExceptionHandler.NotFoundException with parameter exception {}.", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

    @ExceptionHandler(value = DependencyFailureException.class)
    public ResponseEntity<DependencyFailureException> dependencyFailureException(DependencyFailureException exception) {
        log.info("Entering OrderServiceExceptionHandler.DependencyFailureException with parameter exception {}.", exception);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(exception);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationException> validationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ValidationException("Validation Error ", processFieldErrors(fieldErrors)));
    }

    private List<String> processFieldErrors(List<FieldError> fieldErrors) {
        List<String> validationList = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            validationList.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        return validationList;
    }
}
