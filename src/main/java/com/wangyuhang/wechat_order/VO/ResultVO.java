package com.wangyuhang.wechat_order.VO;

import lombok.Data;

/**
 * HTTP请求返回的最外层对象
 */
@Data
public class ResultVO<T>{

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String message;

    /** 具体内容. */
    private T data;
}
