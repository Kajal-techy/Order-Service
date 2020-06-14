package com.orderService.OrderService.service;

import com.orderService.OrderService.model.Order;
import com.orderService.OrderService.model.OrderDTO;
import com.orderService.OrderService.model.Shipment;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface OrderService {

    public Order createOrder(OrderDTO orderDTO) throws ExecutionException, InterruptedException;

    public Order getOrderById(String orderId);

    public Order updateOrderById(String orderId, Shipment shipmentId);

    public List<Order> getAllOrdersByOrderId(List<String> ids);
}
