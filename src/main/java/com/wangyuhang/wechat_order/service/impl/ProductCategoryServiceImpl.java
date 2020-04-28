package com.wangyuhang.wechat_order.service.impl;

import com.wangyuhang.wechat_order.bean.ProductCategory;
import com.wangyuhang.wechat_order.mapper.ProductCategoryMapper;
import com.wangyuhang.wechat_order.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategory findOne(Integer id) {
        return productCategoryMapper.findProductCategoryById(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryMapper.findAllProductCategory();
    }

    @Override
    public void save(ProductCategory productCategory) {
        productCategoryMapper.insertNewProductCategory(productCategory);
    }

    @Override
    public Integer update(ProductCategory productCategory) {
        return productCategoryMapper.updateProductCategory(productCategory);
    }

    @Override
    public List<ProductCategory> findCategoryByTypes(List<Integer> types) {
        return productCategoryMapper.findProductCategoriesByTypes(types);
    }
}
