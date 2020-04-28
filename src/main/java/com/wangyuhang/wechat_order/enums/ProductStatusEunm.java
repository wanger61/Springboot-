package com.wangyuhang.wechat_order.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEunm implements CodeEnum {
    UP(0,"上架"),DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductStatusEunm(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
