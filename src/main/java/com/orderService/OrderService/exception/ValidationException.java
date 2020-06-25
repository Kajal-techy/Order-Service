package com.orderService.OrderService.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties({"trace", "stackTrace", "cause", "suppressed", "localizedMessage", "message"})
public class ValidationException extends RuntimeException {

    List<String> errors;

    public ValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
