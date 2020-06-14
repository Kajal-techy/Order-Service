package com.orderService.OrderService.model;

import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderFactory {

    public Order getOrder(String orderType, String productId, String userId, int quantity) {
        if (orderType == null)
            return null;

        else if (orderType.equalsIgnoreCase("MAGIC"))
            return new MagicOrder(productId, userId, quantity);

        else if (orderType.equalsIgnoreCase("NORMAL"))
            return new NormalOrder(productId, userId, quantity);
        else
            return null;
    }
}
