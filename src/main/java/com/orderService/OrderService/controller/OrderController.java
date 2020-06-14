package com.orderService.OrderService.controller;

import com.orderService.OrderService.model.Order;
import com.orderService.OrderService.model.OrderDTO;
import com.orderService.OrderService.model.Shipment;
import com.orderService.OrderService.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * This function will create order
     *
     * @param order
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO order) throws ExecutionException, InterruptedException {
        Order orderCreated = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderCreated);
    }

    /**
     * This function will return order as per orderID
     *
     * @param id
     * @return
     */
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok().body(order);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrderById(@PathVariable String id, @RequestBody Shipment shipmentId) {
        Order order = orderService.updateOrderById(id, shipmentId);
        return ResponseEntity.ok().body(order);
    }
}
