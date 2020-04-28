package com.wangyuhang.wechat_order.service.impl;

import com.wangyuhang.wechat_order.bean.SellerInfo;
import com.wangyuhang.wechat_order.mapper.SellerInfoMapper;
import com.wangyuhang.wechat_order.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoMapper sellerInfoMapper;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openId) {
        return sellerInfoMapper.findByOpenid(openId);
    }
}
