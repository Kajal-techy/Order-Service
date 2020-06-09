package com.orderService.OrderService.service;

import com.orderService.OrderService.model.Order;
import com.orderService.OrderService.model.OrderDTO;

import java.util.concurrent.ExecutionException;

public interface OrderService {

    public Order createOrder(OrderDTO orderDTO) throws ExecutionException, InterruptedException;

    public Order getOrderById(String orderId);
}
