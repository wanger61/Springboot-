package com.wangyuhang.wechat_order.mapper;

import com.wangyuhang.wechat_order.bean.SellerInfo;
import com.wangyuhang.wechat_order.util.KeyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SellerInfoMapperTest {

    @Autowired
    SellerInfoMapper sellerInfoMapper;

    @Test
    void findByOpenid() {
        SellerInfo abc = sellerInfoMapper.findByOpenid("adc");
        System.out.println(abc);
    }

    @Test
    void addNewSeller() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("adc");

        sellerInfoMapper.addNewSeller(sellerInfo);

    }
}