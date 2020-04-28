package com.wangyuhang.wechat_order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wangyuhang.wechat_order.bean.OrderDetail;
import com.wangyuhang.wechat_order.dto.OrderDTO;
import com.wangyuhang.wechat_order.enums.ResultEnum;
import com.wangyuhang.wechat_order.exception.SellException;
import com.wangyuhang.wechat_order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){

        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e){
            log.error("对象转换错误， string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
