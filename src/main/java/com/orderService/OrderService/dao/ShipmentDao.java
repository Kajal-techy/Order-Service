package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.Shipment;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShipmentDao {

    Optional<Shipment> save(Shipment shipment);

    Optional<Shipment> getShipmentById(String id);
}
