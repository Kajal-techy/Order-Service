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
    public ResponseEntity<InvalidType> notFoundException(InvalidType exception) {
        log.info("Entering OrderServiceExceptionHandler.InvalidType with parameter exception {}.", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

}
