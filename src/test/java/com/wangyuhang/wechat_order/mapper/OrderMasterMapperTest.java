package com.wangyuhang.wechat_order.mapper;

import com.wangyuhang.wechat_order.bean.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterMapperTest {

    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Test
    void findByBuyerOpenid() {
        List<OrderMaster> byBuyerOpenid = orderMasterMapper.findByBuyerOpenid("188188");
        System.out.println(byBuyerOpenid);
    }

    @Test
    void addNewOrderMaster() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("Li");
        orderMaster.setBuyerPhone("123456789");
        orderMaster.setBuyerAddress("东大街");
        orderMaster.setBuyerOpenid("188188");
        orderMaster.setOrderAmount(new BigDecimal(5.6));
        orderMasterMapper.addNewOrderMaster(orderMaster);
    }

    @Test
    void deleteOrderMaster() {
    }
}