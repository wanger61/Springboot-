package com.wangyuhang.wechat_order.handler;

import com.wangyuhang.wechat_order.VO.ResultVO;
import com.wangyuhang.wechat_order.config.ProjectUrlConfig;
import com.wangyuhang.wechat_order.exception.SellException;
import com.wangyuhang.wechat_order.exception.SellerAuthorizeException;
import com.wangyuhang.wechat_order.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    //拦截登录异常
    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:" + projectUrlConfig.getWechatOpenAuthorize()
                + "/sell/wechat/qrAuthorize"
                + "?returnUrl="
                + "/sell/seller/login");
    }

    @ExceptionHandler(SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
