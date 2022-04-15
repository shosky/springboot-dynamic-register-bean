package com.leo.springboot.dynamic.register.bean.bridgepattern;

import com.leo.springboot.dynamic.register.bean.bridgepattern.mode.PayModeService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: leo wang
 * @date: 2022-04-15
 * @description:
 **/
@Component
public class PayModeFactory implements ApplicationContextAware {

    protected static Map<String, PayModeService> payModeServiceMap = new HashMap<>();

    public static PayModeService getPayMode(String mode) {
        return payModeServiceMap.get(mode);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        context.getBeansOfType(PayModeService.class).forEach(payModeServiceMap::put);
    }
}
