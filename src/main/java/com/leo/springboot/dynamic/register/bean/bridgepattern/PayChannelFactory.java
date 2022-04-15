package com.leo.springboot.dynamic.register.bean.bridgepattern;

import com.leo.springboot.dynamic.register.bean.bridgepattern.channel.AbstractChannelService;
import com.leo.springboot.dynamic.register.bean.bridgepattern.channel.AlipayChannelServiceImpl;
import com.leo.springboot.dynamic.register.bean.bridgepattern.channel.WeixinChannelServiceImpl;
import com.leo.springboot.dynamic.register.bean.bridgepattern.mode.PayModeService;
import com.leo.springboot.dynamic.register.bean.utils.ManualRegistBeanUtil;
import com.leo.springboot.dynamic.register.bean.utils.Name;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author: leo wang
 * @date: 2022-04-15
 * @description:
 **/
@Component
public class PayChannelFactory {

    private ApplicationContext context;

    private static Map<String, Class> channelMap = new HashMap<>();

    static {
        Reflections reflections = new Reflections("com.leo.springboot.dynamic.register.bean.bridgepattern.channel");
        reflections.getSubTypesOf(AbstractChannelService.class).stream().forEach(item -> {
            Name annotation = item.getDeclaredAnnotation(Name.class);
            channelMap.put(annotation.value(), item);
        });
    }

    public PayChannelFactory(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    public AbstractChannelService getChannelService(String channel, String mode) {
        PayModeService modeService = PayModeFactory.getPayMode(mode);
        String beanName = new StringBuffer(channel).append(mode).toString();
        AbstractChannelService channelService = (AbstractChannelService) ManualRegistBeanUtil.registerBean
                ((ConfigurableApplicationContext) context, beanName, channelMap.get(channel), modeService);
        return channelService;
    }

}
