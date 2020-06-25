package com.orderService.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

    @NotBlank(message = "productId.required")
    String productId;

    @NotBlank(message = "userId.required")
    String userId;

    @Min(value = 1, message = "quantity.required")
    int quantity;

    int points;
}
