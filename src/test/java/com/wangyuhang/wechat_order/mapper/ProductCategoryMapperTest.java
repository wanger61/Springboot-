package com.wangyuhang.wechat_order.mapper;

import com.wangyuhang.wechat_order.bean.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryMapperTest {

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Test
    void findAllProductCategory() {
        List<ProductCategory> allProductCategory = productCategoryMapper.findAllProductCategory();
        System.out.println(allProductCategory);
    }

    @Test
    void findProductCategoryById() {
        ProductCategory productCategoryById = productCategoryMapper.findProductCategoryById(1);
        System.out.println(productCategoryById);
    }

    @Test
    void findProductCategoriesById() {
        List<Integer> types = Arrays.asList(1, 4);
        List<ProductCategory> productCategoriesByTypes = productCategoryMapper.findProductCategoriesByTypes(types);
        System.out.println(productCategoriesByTypes);
    }

    @Test
    void insertNewProductCategory() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("热销榜");
        productCategory.setCategoryType(2);
        productCategoryMapper.insertNewProductCategory(productCategory);
    }

    @Test
    void deleteProductCategoryById() {
        productCategoryMapper.deleteProductCategoryById(3);
    }

    @Test
    void updateProductCategory() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(1);
        productCategory.setCategoryName("热销榜2");
        productCategory.setCategoryType(2);
        productCategoryMapper.updateProductCategory(productCategory);
    }
}