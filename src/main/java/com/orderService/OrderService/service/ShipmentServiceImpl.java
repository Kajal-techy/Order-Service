package com.orderService.OrderService.service;

import com.orderService.OrderService.dao.ShipmentDao;
import com.orderService.OrderService.exception.NotFoundException;
import com.orderService.OrderService.model.Order;
import com.orderService.OrderService.model.Shipment;
import com.orderService.OrderService.model.ShipmentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {

    private ShipmentDao shipmentDao;

    private OrderService orderService;

    public ShipmentServiceImpl(ShipmentDao shipmentDao, OrderService orderService) {
        this.shipmentDao = shipmentDao;
        this.orderService = orderService;
    }

    @Override
    public Shipment createShipment(ShipmentRequest shipmentRequest) {
        List<Order> orders = new ArrayList<>();
        List<Order> ordersFetched = orderService.getAllOrdersByOrderId(shipmentRequest.getOrderIds());
        Shipment shipment = Shipment.builder().courierName(shipmentRequest.getCourierName())
                .destinationAddress(shipmentRequest.getDestinationAddress())
                .packageWeight(shipmentRequest.getPackageWeight())
                .sourceAddress(shipmentRequest.getSourceAddress())
                .orders(ordersFetched)
                .build();

        Optional<Shipment> shipmentCreated = shipmentDao.save(shipment);

        if (shipmentCreated.isPresent()) {
            ordersFetched.forEach(orderFetched -> orderService.updateOrderById(orderFetched.getOrderId(), shipmentCreated.get()));
        } else
            throw new NotFoundException("Shipment details not saved");
        return shipmentCreated.get();
    }

    /**
     * This function returns the shipment details by shipmentId
     * @param shipmentId
     * @return
     */
    @Override
    public Shipment getShipmentById(String shipmentId) {
        Optional<Shipment> shipment = shipmentDao.getShipmentById(shipmentId);
        if (shipment.isPresent())
            return shipment.get();
        else
            throw new NotFoundException("Shipment details not found");
    }
}
