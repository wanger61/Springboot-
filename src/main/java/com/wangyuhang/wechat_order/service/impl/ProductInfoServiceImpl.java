package com.wangyuhang.wechat_order.service.impl;

import com.github.pagehelper.PageHelper;
import com.wangyuhang.wechat_order.bean.ProductInfo;
import com.wangyuhang.wechat_order.dto.CartDTO;
import com.wangyuhang.wechat_order.enums.ProductStatusEunm;
import com.wangyuhang.wechat_order.enums.ResultEnum;
import com.wangyuhang.wechat_order.exception.SellException;
import com.wangyuhang.wechat_order.mapper.ProductInfoMapper;
import com.wangyuhang.wechat_order.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;


    @Override
    public ProductInfo findOne(String productId) {
        return productInfoMapper.findProductInfoById(productId);
    }

    @Override
    public List<ProductInfo> findAllUp() {
        return productInfoMapper.findProductInfoByState(ProductStatusEunm.UP.getCode());
    }

    @Override
    public List<ProductInfo> findAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<ProductInfo> allProductInfos = productInfoMapper.findAllProductInfos();
        return allProductInfos;
    }

    @Override
    public Integer update(ProductInfo productInfo) {
        return productInfoMapper.updateProductInfo(productInfo);
    }

    @Override
    public void save(ProductInfo productInfo) {
        productInfoMapper.addNewProductInfo(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = productInfoMapper.findProductInfoById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoMapper.updateProductInfo(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList){
            ProductInfo productInfo = productInfoMapper.findProductInfoById(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int res = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (res < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(res);
            productInfoMapper.updateProductInfo(productInfo);

        }
    }

    @Override
    public Integer onSale(String productId) {
        ProductInfo productInfoById = productInfoMapper.findProductInfoById(productId);
        if (productInfoById == null){
            log.error("商品上架时商品不存在,productId={}",productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfoById.getProductState() == ProductStatusEunm.UP.getCode()){
            log.error("商品上架时商品状态错误,productId={}",productId);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfoById.setProductState(ProductStatusEunm.UP.getCode());
        Integer res = productInfoMapper.updateProductInfo(productInfoById);
        if (res <= 0){
            log.error("商品上架时商品更新失败,productId={}",productId);
            throw new SellException(ResultEnum.PRODUCT_UPDATE_ERROR);
        }
        return res;
    }

    @Override
    public Integer offSale(String productId) {
        ProductInfo productInfoById = productInfoMapper.findProductInfoById(productId);
        if (productInfoById == null){
            log.error("商品下架时商品不存在,productId={}",productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfoById.getProductState() == ProductStatusEunm.DOWN.getCode()){
            log.error("商品下架时商品状态错误,productId={}",productId);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfoById.setProductState(ProductStatusEunm.DOWN.getCode());
        Integer res = productInfoMapper.updateProductInfo(productInfoById);
        if (res <= 0){
            log.error("商品上架时商品更新失败,productId={}",productId);
            throw new SellException(ResultEnum.PRODUCT_UPDATE_ERROR);
        }
        return res;
    }

    @Override
    public Integer totalPage(Integer size) {
        Integer totalNum = productInfoMapper.getTotalNum();
        return (int)Math.ceil(totalNum/(size * 1.0));
    }
}
