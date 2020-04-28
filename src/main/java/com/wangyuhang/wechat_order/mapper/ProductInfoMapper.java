package com.wangyuhang.wechat_order.mapper;

import com.wangyuhang.wechat_order.bean.ProductInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductInfoMapper {

    @Select("select * from product_info where product_id = #{productId}")
    public ProductInfo findProductInfoById(String productId);

    @Select("select * from product_info")
    public List<ProductInfo> findAllProductInfos();

    @Select("select * from product_info where product_state = #{productState}")
    public List<ProductInfo> findProductInfoByState(Integer productState);

    @Insert("insert into product_info(product_id, product_name, product_price, product_stock, product_description, product_icon, product_state, category_type) values (#{productId}, #{productName}, #{productPrice}, #{productStock}, #{productDescription}, #{productIcon}, #{productState}, #{categoryType}) ")
    public void addNewProductInfo(ProductInfo productInfo);

    @Delete("delete from product_info where product_id = #{productId}")
    public void deleteProductInfo(String productId);

    @Update("update product_info set product_name = #{productName}, product_price = #{productPrice}, product_stock = #{productStock}, product_description = #{productDescription}, product_icon = #{productIcon}, product_state = #{productState}, category_type = #{categoryType} where product_id = #{productId}")
    public Integer updateProductInfo(ProductInfo productInfo);

    @Select("select count(*) from product_info")
    public Integer getTotalNum();

}
