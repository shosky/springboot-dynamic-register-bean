package com.leo.springboot.dynamic.register.bean.bridgepattern.mode;

import java.math.BigDecimal;

/**
 * @author: leo wang
 * @date: 2022-04-15
 * @description:
 **/
public interface PayModeService {

    String pay(String uid, String orderId, BigDecimal amout,String channel);

    String getName();
}
