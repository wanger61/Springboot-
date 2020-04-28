package com.wangyuhang.wechat_order.mapper;

import com.wangyuhang.wechat_order.bean.SellerInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface SellerInfoMapper {

    @Select("select * from seller_info where openid = #{openid}")
    SellerInfo findByOpenid(String openid);

    @Insert("insert into seller_info(seller_id, username, password, openid) values(#{sellerId}, #{username}, #{password}, #{openid})")
    Integer addNewSeller(SellerInfo sellerInfo);
}
