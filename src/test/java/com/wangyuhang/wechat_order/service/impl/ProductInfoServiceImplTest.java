package com.wangyuhang.wechat_order.service.impl;

import com.wangyuhang.wechat_order.bean.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoServiceImpl productInfoServiceImpl;

    @Test
    void findOne() {
        ProductInfo one = productInfoServiceImpl.findOne("1");
        System.out.println(one);
    }

    @Test
    void findAllUp() {
        List<ProductInfo> allUp = productInfoServiceImpl.findAllUp();
        System.out.println(allUp);
    }

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("2");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.6));
        productInfo.setProductStock(500);
        productInfo.setProductDescription("正宗皮蛋粥");
        productInfo.setProductIcon("http://xxxs.jpg");
        productInfo.setProductState(0);
        productInfo.setCategoryType(3);
        productInfoServiceImpl.save(productInfo);
    }

    @Test
    void on_sale(){
        productInfoServiceImpl.onSale("1");
    }

}