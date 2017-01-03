package com.jpyl.music.api.wxpay;


import com.jpyl.music.api.wxpay.util.MD5Util;
import com.jpyl.music.util.LogUtils;

/**
 * 微信支付相关信息
 */
public class WXPayConfig {

    public static String appid = "wx3ad1a7876b71f95f";
    public static String mch_id = "1359665102";//商家号，微信分配。partnerId------
    public static  String notify_url = "http://139.196.208.147/api/muser/wxPaySuccess.action";  //回调地址
    public static String trade_type="APP";
    public static String app_srect = "148eb4fb4e5f1b3a943b88da03d0c9c5";
    public static  String app_key="bbJL7Z75vLVHxvlL4wFvUUCCQM8KC3Av";







    public static String getSign(String goodesc,String nonce_str,String out_trade_no,String total_fee,String spbill_create_ip,String attach){
//        //1 组装
////        String stringA = "appid="+appid+"&attach="+attach+"&body="+goodesc+"&mch_id="+mch_id+"&nonce_str="+ nonce_str+"&notify_url="+notify_url+"&out_trade_no="+out_trade_no+"&spbill_create_ip="+spbill_create_ip+"&total_fee="+total_fee+"&trade_type="+trade_type;
//        String stringA = "appid="+appid+"&body="+goodesc+"&mch_id="+mch_id+"&nonce_str="+ nonce_str+"&notify_url="+notify_url+"&out_trade_no="+out_trade_no+"&spbill_create_ip="+spbill_create_ip+"&total_fee="+total_fee+"&trade_type="+trade_type;
////      2拼接密匙
//        String stringSignTemp = stringA+"&key="+app_key;
//        LogUtils.log("未加密之前:"+stringSignTemp);
//        String sign = MD5Util.MD5Encode(stringSignTemp,"UTF-8").toUpperCase();

        //1 组装
        String stringA = "appid="+appid+"&attach="+attach+"&body="+goodesc+"&mch_id="+mch_id+"&nonce_str="+ nonce_str+"&notify_url="+notify_url+"&out_trade_no="+out_trade_no+"&spbill_create_ip="+spbill_create_ip+"&total_fee="+total_fee+"&trade_type="+trade_type;

//      2拼接密匙
        String stringSignTemp = stringA+"&key="+app_key;
        LogUtils.log("未加密之前:"+stringSignTemp);
        String sign = MD5Util.MD5Encode(stringSignTemp,"UTF-8").toUpperCase();


        return sign;
    }

    public  static String getPaySign(String prepayId,String pacge,String timeStamp,String nonceStr){
        // 参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay
        //签名中的key一定要小写，不要相信光放文档所说的 特别是这个noncestr  注意注意注意
//        String stringA = "appid="+appid+"&noncestr="+nonceStr+"&package="+pacge+"&partnerid="+mch_id+"&prepayid="+prepayId+"&timestamp="+timeStamp;
//        String stringSignTemp = stringA+"&key="+app_key;
//        String paySign = MD5Util.MD5Encode(stringSignTemp,"UTF-8").toUpperCase();
//        LogUtils.log("paySign---------"+paySign);

        String stringA = "appid="+appid+"&noncestr="+nonceStr+"&package="+pacge+"&partnerid="+mch_id+"&prepayid="+prepayId+"&timestamp="+timeStamp;
        String stringSignTemp = stringA+"&key="+app_key;
        String paySign = MD5Util.MD5Encode(stringSignTemp,"UTF-8").toUpperCase();
        LogUtils.log("paySign---------"+paySign);

        return paySign;
    }
}
