package com.jpyl.music.api.wxpay.util;

import java.util.Random;

public class WXUtil {

    public static String getNonceStr() {
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
    }

    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
