package com.wangyuhang.wechat_order.controller;

import com.wangyuhang.wechat_order.bean.SellerInfo;
import com.wangyuhang.wechat_order.config.ProjectUrlConfig;
import com.wangyuhang.wechat_order.constant.RedisConstant;
import com.wangyuhang.wechat_order.enums.ResultEnum;
import com.wangyuhang.wechat_order.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(String openid, HttpServletResponse httpServletResponse,
                              Map<String, String> map){
        //1. openid去和数据库里的数据匹配
        SellerInfo sellerInfoByOpenid = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfoByOpenid == null){
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        //2.设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        //3.设置token至Cookie
        Cookie cookie = new Cookie("token",token);
        cookie.setPath("/");
        cookie.setMaxAge(expire);
        httpServletResponse.addCookie(cookie);

        //地址跳转时用绝对地址
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,
                       Map<String, String> map){
        //1.从Cookie里查询
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie: cookies){
                if (cookie.getName() == "token"){
                    //2.清除Redis
                    redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
                    //3.清除Cookie
                    cookie.setMaxAge(0); //设置时间为0使其失效
                }
            }
        }

        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");

        return new ModelAndView("common/success",map);
    }
}
