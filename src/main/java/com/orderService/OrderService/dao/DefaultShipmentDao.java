package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultShipmentDao extends JpaRepository<Shipment, String> {
}
