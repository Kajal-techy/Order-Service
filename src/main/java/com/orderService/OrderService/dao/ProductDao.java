package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {

    public Product getProductByProductId(String id);
}
