package com.orderService.OrderService.controller;

import com.orderService.OrderService.model.Shipment;
import com.orderService.OrderService.model.ShipmentRequest;
import com.orderService.OrderService.service.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class ShipmentController {

    private ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/shipment")
    public ResponseEntity<Shipment> createShipment(@Valid @RequestBody ShipmentRequest shipmentRequest) throws ExecutionException, InterruptedException {
        Shipment shipmentCreated = shipmentService.createShipment(shipmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(shipmentCreated);
    }

    @GetMapping("/shipment/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable String id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(shipment);
    }
}
