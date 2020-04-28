package com.wangyuhang.wechat_order.util;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间 + 随机数
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        //6位随机数
        Integer number = random.nextInt(900000) + 100000;

        String key = String.valueOf(number) + System.currentTimeMillis();
        return key;
    }

}
