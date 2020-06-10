package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    public User getUserByUserId(String id, String loggedInUserId);
}
