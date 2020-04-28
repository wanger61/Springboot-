package com.wangyuhang.wechat_order.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangyuhang.wechat_order.enums.ProductStatusEunm;
import com.wangyuhang.wechat_order.util.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductInfo {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    //小图
    private String productIcon;

    //商品状态：0正常，1下架
    private Integer productState = ProductStatusEunm.UP.getCode();

    private Integer categoryType;

    private Date createTime;

    @JsonIgnore
    public ProductStatusEunm getProductStatusEunm(){
        return EnumUtil.getByCode(productState,ProductStatusEunm.class);
    }

}
