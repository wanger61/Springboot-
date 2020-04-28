package com.wangyuhang.wechat_order.controller;

import com.github.pagehelper.PageHelper;
import com.wangyuhang.wechat_order.VO.ResultVO;
import com.wangyuhang.wechat_order.converter.OrderForm2OrderDTOConverter;
import com.wangyuhang.wechat_order.dto.OrderDTO;
import com.wangyuhang.wechat_order.enums.ResultEnum;
import com.wangyuhang.wechat_order.exception.SellException;
import com.wangyuhang.wechat_order.form.OrderForm;
import com.wangyuhang.wechat_order.service.BuyerService;
import com.wangyuhang.wechat_order.service.OrderService;
import com.wangyuhang.wechat_order.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("创建订单参数不正确，orderForm = {}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (orderDTO.getOrderDetailList().size() == 0){
            log.error("创建订单购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    //订单列表
    @RequestMapping("list")
    public ResultVO<List<OrderDTO>> list(String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10")Integer size){

        if (StringUtils.isEmpty(openid)){
            log.error("查询订单列表openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageHelper.startPage(page,size);
        List<OrderDTO> list = orderService.findList(openid);

        return ResultVOUtil.success(list);
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(String openid, String orderid){
        OrderDTO orderOne = buyerService.findOrderOne(openid, orderid);
        return ResultVOUtil.success(orderOne);
    }

    //取消订单
    @PostMapping("cancel")
    public ResultVO cancel(String openid, String orderid){
        buyerService.cancelOrder(openid,orderid);
        return ResultVOUtil.success();
    }
}
