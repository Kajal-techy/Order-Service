package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.Shipment;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class ShipmentDaoImpl implements ShipmentDao {

    private DefaultShipmentDao defaultShipmentDao;

    public ShipmentDaoImpl(DefaultShipmentDao defaultShipmentDao) {
        this.defaultShipmentDao = defaultShipmentDao;
    }

    @Override
    public Optional<Shipment> save(Shipment shipment) {
        Shipment shipmentCreated = defaultShipmentDao.save(shipment);
        return Optional.of(shipmentCreated);
    }

    @Override
    public Optional<Shipment> getShipmentById(String id) {
        return defaultShipmentDao.findById(id);
    }
}
