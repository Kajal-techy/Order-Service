package com.orderService.OrderService.service;

import com.orderService.OrderService.dao.OrderDao;
import com.orderService.OrderService.dao.ProductDao;
import com.orderService.OrderService.dao.UserDao;
import com.orderService.OrderService.exception.InvalidType;
import com.orderService.OrderService.exception.NotFoundException;
import com.orderService.OrderService.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    private UserDao userDao;

    private ProductDao productDao;

    private OrderFactory orderFactory;

    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, ProductDao productDao, OrderFactory orderFactory) {

        this.orderDao = orderDao;
        this.userDao = userDao;
        this.productDao = productDao;
        this.orderFactory = orderFactory;
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
        } catch (ExecutionException exception) {
            log.info("Exception : " + exception.getMessage());
            throw new InvalidType("Either user or product not found. Detailed message : " + exception.getMessage());
        }
        if (user.getUserType() == UserType.MAGIC && product.getProductType() == ProductType.MAGIC)
            order = orderFactory.getOrder("MAGIC", orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity());
        else if (user.getUserType() == UserType.MAGIC && product.getProductType() == ProductType.NORMAL)
            order = orderFactory.getOrder("NORMAL", orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity());
        else if (user.getUserType() == UserType.NORMAL && product.getProductType() == ProductType.MAGIC)
            order = orderFactory.getOrder("NORMAL", orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity());
        else if (user.getUserType() == UserType.NORMAL && product.getProductType() == ProductType.NORMAL)
            order = orderFactory.getOrder("NORMAL", orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getQuantity());
        else
            throw new InvalidType("Either userType = " + user.getUserType() + " or productType = " + product.getProductType() + " is invalid");
        Optional<Order> savedOrder = orderDao.save(order);
        return savedOrder.get();
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

    /**
     * This function update order by OrderId
     * @param orderId
     * @param shipment
     * @return
     */

    @Override
    public Order updateOrderById(String orderId, Shipment shipment) {
        Optional<Order> orderFetched = orderDao.getOrderById(orderId);
        if (orderFetched.isPresent()) {
            orderFetched.get().setShipment(shipment);
            Optional<Order> orderUpdated = orderDao.save(orderFetched.get());
            if (orderUpdated.isPresent())
                return orderUpdated.get();
        }
        throw new NotFoundException("No Data Found");    }

    /**
     * This function returns all exists order
     * @param ids
     * @return
     */
    @Override
    public List<Order> getAllOrdersByOrderId(List<String> ids) {
        Optional<List<Order>> orders = orderDao.getAllOrdersByIds(ids);
        if (orders.isPresent())
            return orders.get();
        else
            throw new NotFoundException("No Data Found");
    }

}
