package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDao {

    Optional<Order> save(Order order);

    Optional<Order> getOrderById(String id);

    Optional<List<Order>> getAllOrdersByIds(List<String> ids);
}
