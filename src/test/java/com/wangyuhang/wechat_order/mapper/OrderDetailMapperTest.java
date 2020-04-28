package com.wangyuhang.wechat_order.mapper;

import com.wangyuhang.wechat_order.bean.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDetailMapperTest {

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Test
    void findByOrderId() {
        List<OrderDetail> byOrderId = orderDetailMapper.findByOrderId("123456");
        System.out.println(byOrderId);
    }

    @Test
    void addNewOrderDetail() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("123456");
        orderDetail.setProductId("1");
        orderDetail.setProductName("小笼包");
        orderDetail.setProductPrice(new BigDecimal(5.2));
        orderDetail.setProductQuantity(5);
        orderDetail.setProductIcon("xxxx");
        orderDetailMapper.addNewOrderDetail(orderDetail);
    }
}