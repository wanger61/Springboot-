package com.wangyuhang.wechat_order.service.impl;

import com.wangyuhang.wechat_order.bean.OrderDetail;
import com.wangyuhang.wechat_order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "123456789";

    private final String ORDER_ID = "1198451587367294207";
    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("Jisoo");
        orderDTO.setBuyerAddress("Korean");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("1889653565");

        List<OrderDetail> orderDetailsList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(2);
        orderDetailsList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(3);
        orderDetailsList.add(o2);

        orderDTO.setOrderDetailList(orderDetailsList);

        OrderDTO result = orderService.create(orderDTO);
        System.out.println(result);


    }

    @Test
    void findOne() {
        OrderDTO one = orderService.findOne(ORDER_ID);
        System.out.println(one);
    }

    @Test
    void findList() {
        List<OrderDTO> list = orderService.findList(BUYER_OPENID);
        System.out.println(list);
    }

    @Test
    void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO cancel = orderService.cancel(orderDTO);
        System.out.println(cancel);
    }

    @Test
    void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO res = orderService.finish(orderDTO);
        System.out.println(res);
    }


    @Test
    void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO res = orderService.paid(orderDTO);
        System.out.println(res);
    }

}