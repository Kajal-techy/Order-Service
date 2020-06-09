package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.Order;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {

    private DefaultOrderDao defaultOrderDao;

    public OrderDaoImpl(DefaultOrderDao defaultOrderDao) {
        this.defaultOrderDao = defaultOrderDao;
    }

    @Override
    public Optional<Order> save(Order order) {
        Order orderCreated = defaultOrderDao.save(order);
        return Optional.of(orderCreated);
    }

    @Override
    public Optional<Order> getOrderById(String id) {
        return defaultOrderDao.findById(id);
    }
}
