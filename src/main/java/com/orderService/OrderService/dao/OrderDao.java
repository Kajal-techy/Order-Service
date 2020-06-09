package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.Order;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDao {

    Optional<Order> save(Order product);

    Optional<Order> getOrderById(String id);
}
