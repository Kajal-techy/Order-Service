package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductServiceProxy {

    @GetMapping("/api/products/{id}")
    Product getProductByProductId(@PathVariable("id") String id);
}
