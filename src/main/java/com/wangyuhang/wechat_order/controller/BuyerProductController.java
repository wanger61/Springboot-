package com.wangyuhang.wechat_order.controller;

import com.wangyuhang.wechat_order.VO.ProductInfoVO;
import com.wangyuhang.wechat_order.VO.ProductVO;
import com.wangyuhang.wechat_order.VO.ResultVO;
import com.wangyuhang.wechat_order.bean.ProductCategory;
import com.wangyuhang.wechat_order.bean.ProductInfo;
import com.wangyuhang.wechat_order.service.ProductCategoryService;
import com.wangyuhang.wechat_order.service.ProductInfoService;
import com.wangyuhang.wechat_order.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findAllUp();

        //查询类目（一次性查询）
        List<Integer> categoryType = new ArrayList<>();
        for (ProductInfo productInfo: productInfoList){
            categoryType.add(productInfo.getCategoryType());
        }
        List<ProductCategory> categoryByTypes = productCategoryService.findCategoryByTypes(categoryType);

        //数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: categoryByTypes){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        ResultVO resultVO = ResultVOUtil.success(productVOList);
        return resultVO;
    }
}
