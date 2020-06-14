package com.orderService.OrderService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("MagicOrder")
@Getter
@Setter
@NoArgsConstructor
public class MagicOrder extends Order {

    public static final int SHIPMENT_DELIVERY_ADDITION = 2;
    public int points = 1;

    public MagicOrder(String productId, String userId, int quantity) {
        super(productId, userId, quantity);
        this.expectedDeliveryDate = LocalDateTime.now().plusDays(SHIPMENT_DELIVERY_ADDITION);
        this.points = quantity * points;
    }
}
