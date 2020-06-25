package com.orderService.OrderService.model;

import com.orderService.OrderService.exception.InvalidType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderFactory {

    public Order getOrder(UserType userType, ProductType productType, String productId, String userId, int quantity) {
        if (userType == UserType.MAGIC && productType == ProductType.MAGIC)
            return new MagicOrder(productId, userId, quantity);
        else if (userType == UserType.MAGIC && productType == ProductType.NORMAL)
            return new NormalOrder(productId, userId, quantity);
        else if (userType == UserType.NORMAL && productType == ProductType.MAGIC)
            return new NormalOrder(productId, userId, quantity);
        else if (userType == UserType.NORMAL && productType == ProductType.NORMAL)
            return new NormalOrder(productId, userId, quantity);
        else
            throw new InvalidType("Either userType = " + userType + " or productType = " + productType + " is invalid");
    }
}


