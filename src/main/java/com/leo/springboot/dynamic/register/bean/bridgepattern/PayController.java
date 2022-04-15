package com.leo.springboot.dynamic.register.bean.bridgepattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @author: leo wang
 * @date: 2022-04-15
 * @description:
 **/
@Controller
@RequestMapping("v1/pay")
public class PayController {

    @Autowired
    private PayChannelFactory payChannelFactory;

    @ResponseBody
    @PostMapping("dopay")
    public String pay(@RequestParam String uId, @RequestParam BigDecimal amount, @RequestParam String channel, @RequestParam String mode) {
        return payChannelFactory.getChannelService(channel, mode).doPay(uId, amount);
    }
}
