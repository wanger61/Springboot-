package com.wangyuhang.wechat_order.service.impl;

import com.wangyuhang.wechat_order.dto.OrderDTO;
import com.wangyuhang.wechat_order.service.OrderService;
import com.wangyuhang.wechat_order.service.PayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create(){
        OrderDTO orderDTO = orderService.findOne("1198451587367294207");
        payService.create(orderDTO);
    }

}