package com.leo.springboot.dynamic.register.bean.bridgepattern.mode;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author: leo wang
 * @date: 2022-04-15
 * @description:
 **/
@Service("h5")
public class H5PayModeServiceImpl implements PayModeService {
    @Override
    public String pay(String uid, String orderId, BigDecimal amout, String channel) {
        return System.currentTimeMillis() + "-" + channel + "-" + getName();
    }

    @Override
    public String getName() {
        return "H5";
    }
}
