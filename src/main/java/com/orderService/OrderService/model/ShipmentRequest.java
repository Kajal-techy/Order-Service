package com.orderService.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequest {

    @NotEmpty(message = "courierName.required")
    String courierName;

    @NotBlank(message = "packageWeight.required")
    String packageWeight;

    @NotBlank(message = "sourceAddress.required")
    String sourceAddress;

    @NotBlank(message = "destinationAddress.required")
    String destinationAddress;

    private List<String> orderIds = new ArrayList<>();
}

