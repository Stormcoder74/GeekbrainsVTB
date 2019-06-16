package com.geekbrains.teryaevs.entities;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Statistic {
    @Before("execution(public String com.geekbrains.teryaevs.controllers.ProductsController.showOneProduct(..))") // pointcut expression
    public void beforeAnyMethodInUserDAOClassWithDetails(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("В UserDAO был вызван метод: " + methodSignature);
        Object[] args = joinPoint.getArgs();
            System.out.println("Был просмотрен продукт с ID " + args[1]);
    }
}
