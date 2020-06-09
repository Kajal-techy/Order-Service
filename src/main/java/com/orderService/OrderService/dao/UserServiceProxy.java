package com.orderService.OrderService.dao;

import com.orderService.OrderService.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user-service")
public interface UserServiceProxy {

    @GetMapping("/v1/users/{id}")
    User getUserUserId(@PathVariable("id") String id, @RequestHeader String loggedInUserId);
}
