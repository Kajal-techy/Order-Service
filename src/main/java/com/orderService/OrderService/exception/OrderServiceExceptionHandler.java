package com.orderService.OrderService.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
