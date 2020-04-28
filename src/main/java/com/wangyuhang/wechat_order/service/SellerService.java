package com.wangyuhang.wechat_order.service;

import com.wangyuhang.wechat_order.bean.SellerInfo;
import org.springframework.stereotype.Service;


public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openId);
}
