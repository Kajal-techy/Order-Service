package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {

    private ProductServiceProxy productServiceProxy;

    public ProductDaoImpl(ProductServiceProxy productServiceProxy) {
        this.productServiceProxy = productServiceProxy;
    }

    @Override
    public Product getProductByProductId(String id) {
        Product product = productServiceProxy.getProductByProductId(id);
        return product;
    }
}
