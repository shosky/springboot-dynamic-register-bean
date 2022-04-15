package com.leo.springboot.dynamic.register.bean.bridgepattern.channel;

import com.leo.springboot.dynamic.register.bean.bridgepattern.mode.PayModeService;

import java.math.BigDecimal;

/**
 * @author: leo wang
 * @date: 2022-04-15
 * @description:
 **/
public abstract class AbstractChannelService {

    protected PayModeService payModeService;

    public AbstractChannelService(PayModeService payModeService) {
        this.payModeService = payModeService;
    }

    public abstract String doPay(String uId,BigDecimal amount);

}
