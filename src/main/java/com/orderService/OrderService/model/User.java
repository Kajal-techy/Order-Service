package com.orderService.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {

    private String id;

    private UserType userType;
}
