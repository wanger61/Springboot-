package com.wangyuhang.wechat_order.mapper;

import com.wangyuhang.wechat_order.bean.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductInfoMapperTest {

    @Autowired
    ProductInfoMapper productInfoMapper;


    @Test
    void findProductInfoById() {
        ProductInfo productInfoById = productInfoMapper.findProductInfoById("1");
        System.out.println(productInfoById);
    }

    @Test
    void findAllProductInfos() {
        List<ProductInfo> allProductInfos = productInfoMapper.findAllProductInfos();
        System.out.println(allProductInfos);
    }

    @Test
    void testFindProductInfoByState() {
        List<ProductInfo> productInfoByState = productInfoMapper.findProductInfoByState(0);
        System.out.println(productInfoByState);
    }

    @Test
    void addNewProductInfo() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("小笼包");
        productInfo.setProductPrice(new BigDecimal(5.2));
        productInfo.setProductStock(50);
        productInfo.setProductDescription("正宗小笼包");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductState(0);
        productInfo.setCategoryType(2);
        productInfoMapper.addNewProductInfo(productInfo);
    }

    @Test
    void deleteProductInfo() {
        productInfoMapper.deleteProductInfo("1");
    }

    @Test
    void updateProductInfo() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("小笼包");
        productInfo.setProductPrice(new BigDecimal(5.2));
        productInfo.setProductStock(500);
        productInfo.setProductDescription("正宗小笼包");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductState(0);
        productInfo.setCategoryType(2);
        productInfoMapper.updateProductInfo(productInfo);
    }
}