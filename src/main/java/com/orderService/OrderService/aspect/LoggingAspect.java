package com.orderService.OrderService.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class LoggingAspect {
    @Around("execution(* com.orderService.OrderService.controller.OrderController.*(..)) || execution(* com.orderService.OrderService.service.OrderServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint jointpoint) throws Throwable {
        log.info("Entering in the {}.{} with Parameters {}", jointpoint.getTarget().getClass().getSimpleName(), jointpoint.getSignature().getName(), jointpoint.getTarget().getClass().getTypeParameters());
        long startTime = System.currentTimeMillis();
        Object response = jointpoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Exiting from {}.{} with return value {} and total time: {}", jointpoint.getTarget().getClass().getSimpleName(), jointpoint.getSignature().getName(), response, timeTaken);
        return response;
    }

    @AfterThrowing(pointcut = "execution(* com.orderService.OrderService.controller.OrderController.*(..)) || execution(* com.orderService.OrderService.service.OrderServiceImpl.*(..))", throwing = "ex")
    public void printException(JoinPoint jointpoint, Exception ex) throws Throwable {
        log.info("Exiting from {}.{} with exception {} ", jointpoint.getTarget().getClass().getSimpleName(), jointpoint.getSignature().getName(), ex.toString());
    }
}
