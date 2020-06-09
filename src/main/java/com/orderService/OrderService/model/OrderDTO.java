package com.orderService.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

    @NotBlank(message = "productId.required")
    String productId;

    @NotBlank
    String userId;

    @NotBlank
    int quantity;

    int points;
}
