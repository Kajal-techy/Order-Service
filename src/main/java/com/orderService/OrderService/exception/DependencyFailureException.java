package com.orderService.OrderService.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage"})
public class DependencyFailureException extends RuntimeException {

    public DependencyFailureException(String message) {
        super(message);
    }
}
