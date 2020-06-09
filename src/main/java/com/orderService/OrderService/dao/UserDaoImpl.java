package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserDaoImpl implements UserDao {

    private UserServiceProxy userServiceProxy;

    public UserDaoImpl(UserServiceProxy userServiceProxy) {
        this.userServiceProxy = userServiceProxy;
    }

    @Override
    public User getUserByUserId(String id, String loggedInUserId) {
        User user = userServiceProxy.getUserUserId(id, loggedInUserId);
        log.info("User Details = {} ", user);
        return user;
    }
}
