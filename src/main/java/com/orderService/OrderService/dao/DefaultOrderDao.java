package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultOrderDao extends JpaRepository<Order, String> {
}
