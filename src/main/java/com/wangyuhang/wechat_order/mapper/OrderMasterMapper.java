package com.wangyuhang.wechat_order.mapper;

import com.wangyuhang.wechat_order.bean.OrderMaster;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderMasterMapper {

    @Select("select * from order_master where buyer_openid = #{buyerOpenid}")
    public List<OrderMaster> findByBuyerOpenid(String buyerOpenid);

    @Select("select * from order_master where order_id = #{orderId}")
    public OrderMaster findByOrderId(String orderId);

    @Select("select * from order_master")
    public List<OrderMaster> findAllOrders();

    @Select("select count(*) from order_master")
    public Integer getTotalNum();

    @Insert("insert into order_master(order_id, buyer_name, buyer_phone, buyer_address, buyer_openid,  order_amount, order_status, pay_status) values (#{orderId}, #{buyerName}, #{buyerPhone}, #{buyerAddress}, #{buyerOpenid}, #{orderAmount}, #{orderStatus}, #{payStatus}) ")
    public void addNewOrderMaster(OrderMaster orderMaster);

    @Delete("delete from order_master where order_id = #{orderId}")
    public void deleteOrderMaster(String orderId);

    @Update("update order_master set buyer_name = #{buyerName}, buyer_phone = #{buyerPhone}, buyer_address = #{buyerAddress}, buyer_openid= #{buyerOpenid}, order_amount = #{orderAmount}, order_status = #{orderStatus}, pay_status = #{payStatus} where order_id = #{orderId}")
    public int updateOrderMaster(OrderMaster orderMaster);
}
