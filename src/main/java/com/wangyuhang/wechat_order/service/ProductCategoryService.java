package com.wangyuhang.wechat_order.service;

import com.wangyuhang.wechat_order.bean.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    public ProductCategory findOne(Integer id);

    public List<ProductCategory> findAll();

    public void save(ProductCategory productCategory);

    public Integer update(ProductCategory productCategory);

    public List<ProductCategory> findCategoryByTypes(List<Integer> types);
}
