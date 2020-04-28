package com.wangyuhang.wechat_order.service.impl;

import com.wangyuhang.wechat_order.bean.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryServiceImplTest {

    @Autowired
    ProductCategoryServiceImpl productCategoryServiceImpl;

    @Test
    void findOne() {
        ProductCategory one = productCategoryServiceImpl.findOne(1);
        System.out.println(one);
    }

    @Test
    void findAll() {
        List<ProductCategory> all = productCategoryServiceImpl.findAll();
        System.out.println(all);
    }

    @Test
    void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(4);
        productCategory.setCategoryName("热销饮品");
        productCategoryServiceImpl.save(productCategory);
    }

    @Test
    void findCategoryByTypes() {
        List<Integer> integers = Arrays.asList(2, 3);
        List<ProductCategory> categoryByTypes = productCategoryServiceImpl.findCategoryByTypes(integers);
        System.out.println(categoryByTypes);
    }
}