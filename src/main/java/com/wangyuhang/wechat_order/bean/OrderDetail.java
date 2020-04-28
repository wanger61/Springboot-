package com.wangyuhang.wechat_order.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetail {

    private String detailId;

    /** 订单Id. */
    private String orderId;

    /** 商品Id. */
    private String productId;

    /** 商品名称. */
    private String productName;


    /** 商品单价. */
    private BigDecimal productPrice;

    /** 商品数量. */
    private Integer productQuantity;

    /** 商品小图. */
    private String productIcon;
}
