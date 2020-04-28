package com.wangyuhang.wechat_order.service;

import com.wangyuhang.wechat_order.dto.OrderDTO;

public interface PushMessage {
    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
