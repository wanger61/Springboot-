package com.wangyuhang.wechat_order.bean;

import lombok.Data;

import java.util.Date;

@Data
public class ProductCategory {

    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    public ProductCategory() {
    }

    public ProductCategory(Integer categoryId, String categoryName, Integer categoryType) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
