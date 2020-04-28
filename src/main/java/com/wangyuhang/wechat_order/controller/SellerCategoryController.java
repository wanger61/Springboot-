package com.wangyuhang.wechat_order.controller;

import com.wangyuhang.wechat_order.bean.ProductCategory;
import com.wangyuhang.wechat_order.exception.SellException;
import com.wangyuhang.wechat_order.form.CategoryForm;
import com.wangyuhang.wechat_order.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * 卖家类目
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(required = false) Integer categoryId,
                              Map<String,Object> map){
        if (categoryId != null){
            ProductCategory category = productCategoryService.findOne(categoryId);
            map.put("category",category);
        }

        return new ModelAndView("category/index",map);
    }

    /**
     * 保存/更新
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/update")
    public ModelAndView update(@Valid CategoryForm categoryForm,
                               BindingResult bindingResult,
                               Map<String, Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("commom/error",map);
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            //如果传来数据，则是修改操作，否则是新增操作
            if (categoryForm.getCategoryId() != null){
                productCategory = productCategoryService.findOne(categoryForm.getCategoryId());
                BeanUtils.copyProperties(categoryForm, productCategory);
                productCategoryService.update(productCategory);
            }else {
                BeanUtils.copyProperties(categoryForm, productCategory);
                productCategoryService.save(productCategory);
            }
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("commom/error",map);
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
}
