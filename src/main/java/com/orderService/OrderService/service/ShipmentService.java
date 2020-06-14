package com.orderService.OrderService.service;

import com.orderService.OrderService.model.Shipment;
import com.orderService.OrderService.model.ShipmentRequest;
import org.springframework.stereotype.Service;

@Service
public interface ShipmentService {

    public Shipment createShipment(ShipmentRequest shipmentRequest);

    public Shipment getShipmentById(String shipmentId);
}

