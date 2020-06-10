package com.orderService.OrderService.service;

import com.orderService.OrderService.dao.OrderDao;
import com.orderService.OrderService.dao.ProductDao;
import com.orderService.OrderService.dao.UserDao;
import com.orderService.OrderService.exception.InvalidType;
import com.orderService.OrderService.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.*;

@Service
@Transactional
@Slf4j
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
        User user = null;
        Product product = null;
        Order order = null;
        try {
            user = (User) userFuture.get();
            product = (Product) productFuture.get();
            if (user != null && product != null) {
                if (user.getUserType() == UserType.MAGIC && product.getProductType() == ProductType.MAGIC)
                    order = new MagicOrder(orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity(),
                            orderDTO.getPoints());
                else if (user.getUserType() == UserType.MAGIC && product.getProductType() == ProductType.NORMAL)
                    order = new NormalOrder(orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity());
                else if (user.getUserType() == UserType.NORMAL && product.getProductType() == ProductType.MAGIC)
                    order = new NormalOrder(orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity());
                else if (user.getUserType() == UserType.NORMAL && product.getProductType() == ProductType.NORMAL)
                    order = new NormalOrder(orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity());
            } else {
                log.info("Either UserType or ProductType is null");
                throw new InvalidType("Either UserType or ProductType is null");
            }
            order = orderDao.save(order).get();

        } catch (ExecutionException exception) {
            log.info("Exception : " + exception.getMessage());
            throw new InvalidType("Either UserType or ProductType is invalid" + exception);
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
