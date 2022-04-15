package com.leo.springboot.dynamic.register.bean.bridgepattern.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author: leo wang
 * @date: 2022-04-15
 * @description:
 **/
@Service
public class OrderService {

    public String createOrderDetail(String uId, BigDecimal amout) {
        return "123";
    }
}
