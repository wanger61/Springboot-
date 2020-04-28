package com.wangyuhang.wechat_order.mapper;

import com.wangyuhang.wechat_order.bean.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDetailMapper {

    @Select("select * from order_detail where order_id = #{orderId} ")
    public List<OrderDetail> findByOrderId(String orderId);

    @Insert("insert into order_detail(detail_id, order_id, product_id, product_name, product_price, product_quantity, product_icon) values (#{detailId}, #{orderId}, #{productId}, #{productName}, #{productPrice}, #{productQuantity}, #{productIcon})")
    public void addNewOrderDetail(OrderDetail orderDetail);
}
