package com.orderService.OrderService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("Normal")
@Getter
@Setter
@NoArgsConstructor
public class NormalOrder extends Order {

    public static final int SHIPMENT_DELIVERY_ADDITION = 7;

    public NormalOrder(String productId, String userId, int quantity) {
        super(productId, userId, quantity);
        this.expectedDeliveryDate = LocalDateTime.now().plusDays(SHIPMENT_DELIVERY_ADDITION);
    }
}

