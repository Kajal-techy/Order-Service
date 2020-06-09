package com.orderService.OrderService.service;

import com.orderService.OrderService.dao.OrderDao;
import com.orderService.OrderService.dao.ProductDao;
import com.orderService.OrderService.dao.UserDao;
import com.orderService.OrderService.exception.InvalidType;
import com.orderService.OrderService.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    private UserDao userDao;

    private ProductDao productDao;

    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, ProductDao productDao) {

        this.orderDao = orderDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    /**
     * This function is saving the order details in the database
     *
     * @param orderDTO
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Order createOrder(OrderDTO orderDTO) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future userFuture = executorService.submit((Callable) () -> userDao.getUserByUserId(orderDTO.getUserId(), orderDTO.getUserId()));
        Future productFuture = executorService.submit((Callable) () -> productDao.getProductByProductId(orderDTO.getProductId()));
        executorService.shutdown();

        User user = (User)userFuture.get();
        Product product =  (Product)productFuture.get();

        Order order = null;
        if (user !=  null && product != null) {
            if (user.getUserType() == UserType.MAGIC && product.getProductType() == ProductType.MAGIC)
                order = new MagicOrder(orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity(),
                        orderDTO.getPoints());
            else if (user.getUserType() == UserType.MAGIC && product.getProductType() == ProductType.NORMAL)
                order = new NormalOrder(orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity());
            else if (user.getUserType() == UserType.NORMAL && product.getProductType() == ProductType.MAGIC)
                order = new NormalOrder(orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity());
            else if (user.getUserType() == UserType.NORMAL && product.getProductType() == ProductType.NORMAL)
                order = new NormalOrder(orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity());
            else
                throw new InvalidType("Either UserType : " + user.getUserType() + " or ProductType : " + product.getProductType() + " is invalid");
            order = orderDao.save(order).get();
        }
            return order;
    }

    /**
     * This function is fetching the order details from database
     *
     * @param orderId
     * @return
     */
    @Override
    public Order getOrderById(String orderId) {
        Optional<Order> order = orderDao.getOrderById(orderId);
        return order.orElse(null);
    }
}
