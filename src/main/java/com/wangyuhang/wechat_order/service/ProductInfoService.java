package com.wangyuhang.wechat_order.service;

import com.wangyuhang.wechat_order.bean.ProductInfo;
import com.wangyuhang.wechat_order.dto.CartDTO;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有上架的商品
     * @return
     */
    List<ProductInfo> findAllUp();

    List<ProductInfo> findAll(Integer page, Integer size);

    Integer update(ProductInfo productInfo);

    void save(ProductInfo productInfo);

    Integer totalPage(Integer size);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    Integer onSale(String productId);

    //下架
    Integer offSale(String productId);
}
