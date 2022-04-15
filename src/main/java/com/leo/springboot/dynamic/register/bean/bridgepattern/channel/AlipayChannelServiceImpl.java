package com.leo.springboot.dynamic.register.bean.bridgepattern.channel;

import com.leo.springboot.dynamic.register.bean.bridgepattern.mode.PayModeService;
import com.leo.springboot.dynamic.register.bean.bridgepattern.order.OrderService;
import com.leo.springboot.dynamic.register.bean.utils.Name;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author: leo wang
 * @date: 2022-04-15
 * @description:
 **/
@Name(value = "alipay")
public class AlipayChannelServiceImpl extends AbstractChannelService {

    @Autowired
    private OrderService orderService;

    public AlipayChannelServiceImpl(PayModeService payModeService) {
        super(payModeService);
    }

    @Override
    public String doPay(String uId, BigDecimal amount) {
        //创建预下单
        String orderId = orderService.createOrderDetail(uId, amount);
        //发起支付&返回第三方支付请求参数
        return payModeService.pay(uId, orderId, amount, "alipay");
    }

}
