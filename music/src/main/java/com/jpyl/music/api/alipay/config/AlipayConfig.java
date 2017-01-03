package com.jpyl.music.api.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088511301601640";

	public static String SELLER = "1549803178@qq.com"; //登陆账号吗?
	// 商户的私钥
//	public static String private_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAN7yk8PZAZWM76UykLTg/xgT33fuYhOkjLQZxv39fxFwPz/9g9uA4lFrb2TBM2Ia7ZTj4ueS/k+/4kxzmMQDBUe8UOLgibB8kl8F0N6VoU6qNDCoRRW413WguUlIDLTsK0COzURKI/doWe52wMtETMlOTO6HiNUyeYv/O9gwYoLFAgMBAAECgYEAmghg3PvuI0QHQkxoPAADXsBEMdkqO8YBTGFO4aalhwfVO0HNOveV5Yjjomn1NT/Di43S9AnT2IeLMyTek/Y+S2H66vd6wmLlT0EFC3gnGnIRkQzpdbcCib+Dc/GdZ1GTMks9CCIGGPnZyvIsTkcQGnAOw/NvRxOj+4jTfjFjTfUCQQD9R3iCFjKgExxtjfx/y05jGVKhykHpV8L/qSTXZomTZy2XlB2crd7GA1TlvDsu0bJXO4RqfmoyWH7ErKQD4nG/AkEA4VexclwelEVl33JvTkCYWRWGE8NvoKk4jR7vk4gDG8feWUJHqWf9areXsfvhs1nHtuJxQMtUX9NZm4RQfSkkewJBAJ635Ae5O15eu4jX2myHDgdB/itPIQDGM3edGm41Xm9V0BdWBTyDJMB3pAMXcwsC87yAmplVwNzt4fsDqQCaLecCQGyBcmNTsjHJHN20V55Bglpa/92iXKzq7t0dCVVrx7tnyUwHm0QaW684SiYc2DgpQf5xGQGPJsihl2NIGg26cuMCQQDamdrHQMfXFXw68BWaXATKssMVDcdZ5DrXHhAcrVUppQZ16Ey4XOKDNevqaX+lslUaOi8JXgygGIqEyUXa1EKu";
	
	// 支付宝的公钥，无需修改该值
//	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	public static String ali_public_key="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	public static String private_key="MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAMH9uRslsusCkbkmzO8iecDUKumWecN1i8Hpv6JIeLMHfzVkvfUadB/bV4H/E23nQ0+62jWFWOPH1gpucXyiRWkxqRuGXZuwenYaSsAJx/CXHrozZhagr08J7amwWX3NMyYHQXbhbs2mP5KglXK2tRmG4QvAhEdgmWLfyHEVp6hnAgMBAAECgYEAlx160LbxRTjfI4giJDaK+bdN\tJmFdwPEUSZ449cXWoD+6zHEJuKE+zIIlDNMS444/VSCNUxvpPqcxCTc3mIsf3ZADi7FI/v3K8zflgp/uAc5yk6Dw6St6yVjTY3rHrMeJhqHDiP5j8kbJQ6nZWMH8sM+lF6vRNICJYoZy3YLBwkECQQD9na+P0dJhBZneyuXAGeeaNjWIiODL+k+XNchEjNEjeKlP2yNROrrCL1+fk4nvBtghZGvZH3w/Pxm4zd5N0sLPAkEAw9CNk3npz3TveUteTV4JAXWiwb9fnBVkcSZrJftfJJJwyRR53UgReR3OauiVQwzQlrMV4V83O+uHwQmESmSG6QJBALoUeVynkZlYN7aM3hedqh2uyoZ8D5v58225q1AIleZVwLxzx6zwz1hs09dMtDapqeiOOsca39mz3ZaHjiVBj2cCQQC3Nj/xF6V7VRbnb2xZiT113yIck7maaa2j7OVeEVdQ+a+LhJwNPewTXZ8QCnP121yjVAaJ6zPb3aEULZpYnLxZAkEA1zm/Xd3gBHwmJX495RBXpffL+yulVQnk1YVoKLbUGEqJpPedCp0Lvq5rUu3AEUgqvxqyqL8scz9k534moNjRyg==";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "/home/server/tomcat/alipay_log/";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
