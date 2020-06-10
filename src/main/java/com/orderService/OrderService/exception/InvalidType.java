package com.orderService.OrderService.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage"})
public class InvalidType extends RuntimeException {

    public InvalidType(String message) {
        super(message);
    }
}
