package com.orderService.OrderService.service;

import com.orderService.OrderService.dao.ShipmentDao;
import com.orderService.OrderService.exception.DependencyFailureException;
import com.orderService.OrderService.model.Order;
import com.orderService.OrderService.model.Shipment;
import com.orderService.OrderService.model.ShipmentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentDao shipmentDao;

    private final OrderService orderService;

    public ShipmentServiceImpl(ShipmentDao shipmentDao, OrderService orderService) {
        this.shipmentDao = shipmentDao;
        this.orderService = orderService;
    }

    @Override
    public Shipment createShipment(ShipmentRequest shipmentRequest) {
        List<Order> ordersFetched = orderService.getAllOrdersByOrderId(shipmentRequest.getOrderIds());
        Shipment shipment = Shipment.builder().courierName(shipmentRequest.getCourierName())
                .destinationAddress(shipmentRequest.getDestinationAddress())
                .packageWeight(shipmentRequest.getPackageWeight())
                .sourceAddress(shipmentRequest.getSourceAddress())
                .orders(ordersFetched)
                .build();

        Optional<Shipment> shipmentCreated = shipmentDao.save(shipment);

        if (shipmentCreated.isPresent()) {
            ordersFetched.forEach(orderFetched -> orderService.updateOrderWithShipment(orderFetched.getOrderId(), shipmentCreated.get()));
        } else
            throw new DependencyFailureException("Shipment details not saved");
        return shipmentCreated.get();
    }

    /**
     * This function returns the shipment details by shipmentId
     *
     * @param shipmentId
     * @return
     */
    @Override
    public Shipment getShipmentById(String shipmentId) {
        Optional<Shipment> shipment = shipmentDao.getShipmentById(shipmentId);
        if (shipment.isPresent())
            return shipment.get();
        else
            throw new DependencyFailureException("Shipment details not found with shipmentId: " + shipmentId);
    }
}
