package com.wangyuhang.wechat_order.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductForm {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    //小图
    private String productIcon;

    private Integer categoryType;
}
