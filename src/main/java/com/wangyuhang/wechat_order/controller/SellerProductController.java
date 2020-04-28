package com.wangyuhang.wechat_order.controller;

import com.wangyuhang.wechat_order.bean.ProductCategory;
import com.wangyuhang.wechat_order.bean.ProductInfo;
import com.wangyuhang.wechat_order.dto.OrderDTO;
import com.wangyuhang.wechat_order.exception.SellException;
import com.wangyuhang.wechat_order.form.ProductForm;
import com.wangyuhang.wechat_order.service.ProductCategoryService;
import com.wangyuhang.wechat_order.service.ProductInfoService;
import com.wangyuhang.wechat_order.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){

        List<ProductInfo> list = productInfoService.findAll(page, size);
        map.put("productInfoList",list);

        Integer totalPage = productInfoService.totalPage(size);
        map.put("totalPage",totalPage);
        map.put("currentPage",page);

        return new ModelAndView("product/list",map);
    }

    @GetMapping("/on_sale")
    public ModelAndView onSale(String productId,Map<String,Object> map){
        try{
            productInfoService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(String productId,Map<String,Object> map){
        try{
            productInfoService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(required = false) String productId,Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> categories = productCategoryService.findAll();
        map.put("categories",categories);
        return new ModelAndView("product/index",map);
    }

    /**
     * 保存/更新
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/update")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String,String> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("commom/error",map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //如果为空代表是新增
            if (StringUtils.isEmpty(productForm.getProductId())){
                BeanUtils.copyProperties(productForm,productInfo);
                productInfo.setProductId(KeyUtil.genUniqueKey());
                productInfoService.save(productInfo);
            }else {
                productInfo = productInfoService.findOne(productForm.getProductId());
                BeanUtils.copyProperties(productForm, productInfo);
                productInfoService.update(productInfo);
            }
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("commom/error",map);
        }

        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

}
