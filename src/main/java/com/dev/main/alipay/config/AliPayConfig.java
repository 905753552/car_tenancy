package com.dev.main.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AliPayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 */

public class AliPayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092100560696";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC6cewsZ5xzStGx9+vGltdLFrhn1436M5s/S9g0Rk0dvJf5XoBInQU5LcQzHmAxnSdqgCtMHCPtGnEB/UipT3a+yREXbNal6N7PsrE8IQg0Bh1zZ8BilFgQEAEHdpyX22PIKw3JGciul6xscmZC8bLAk4c53dVz2k2+jrmvujGd//j+YcNVi9sg7586tIN4Q93Awrr1D+x1d6ZI6nXhLRQIG6jrk7aoYLDakoGSIx6d5FSM3BYQ/vXX6M33NJQ73IZxD2eYDrN1g1jLPOcr5g5FiAPnA6yydrRlgd5u/5BMA2hQ7Q8AqCasE81PJC+jPhoge3DXqdI3xQ82ooYpXGCvAgMBAAECggEANUDa42gIwMmLEZktqABnqBPqQgbIj8Fzw2WHcI7v8eFvSxPZ8mOfHVDcLHmtoh4wJvRK6adT/6JAyv+sZGfFFANcNWOp/6N/GLYCd5k7vZhNiywEQGP8r0HS+W3AloNcgc0CWAFcV7oMzsDyQobEtsqsojOhh0DAWgBsOywkhesdBies16Po78rFCi8unjPRVvsrIOmxtr9GKSmQDkbEg3dDxyZLUgM0Eg9vn2zPwgI6W/UzskA0tMaJpryNeENCh4y1XeRJuNqrDKVxl7ChqObQlAlQH3LQU9ilZbQXqR5S6YM8NSjYtEXEo2GOKWgrgXXjFwm9RVOvcORR4KMWgQKBgQDo0Rp0D6QpIuadZUuNc+hyy5bZjarm+Oat4Q3lzWbfo669HujhDzojztzBzbxZIP4HRD8Dk9ypgI2OsJ//lnm0O+3N9nh39Kw9vSKuZYA3X0KF1iv7k/aOcrBng8oYM6Y0TTGj50z/fk2c1LwdnpAV9g2bb6vNVj04Hqmwy0Cd7wKBgQDNArke857La4Pfx/opROpPcceX4YJxiMBw2AISq8jm1TNJjlvV75pNknO9WTQV/KkVVTORKbLI/2qx9VsuDTetJfmc4LvANnOSz5+8m/jS3xlJBNKoLaeGO1eUyEKX0grmo2kQzhi7GJ/s4DwmRdrR4mR4xDKjzueTMse2bucpQQKBgGG6VLZfUnOKGO/880f/XZXCbCIKW7D0zjAzBlhubj52QQVWEj9W2vro/LXFnMh1Fu0J8sGXscrdEY7SCXeKPLAxb9NDILKJ3DtWR1aZeMbFj/EKRBAovhhxgCcTpSOfmqbduHaqwyGEbSPmEMkGJElzraaPq7hX+P6zsFtsFkW1AoGALsaFSGubepxQXXm6OTpRH8k9GglJAvCJv/V5G5t4Xjqc1haWG4zsrvBQWg6FVNLuHUDwYBmcgkHMrUfnAu3fGzGD72hTTjreScv3jUglEK/4i/F+wwKPq4/UnszGbO9ur0rviPcNK3sl2h0bfDwJ/kNiWy+gwkFOEwbODHDna0ECgYEAj41ui3bdqqaTvd2BVy8yTdQzc2NltkwR2e6sme8GfNJuyYEuxMugZf56Go/5QK4KQkv/eCVU0JpEtzsWPLyBbOATK+5zEpCgPCOYnqKFxZ9853Maaw8o3FZayG+AqNFqtnUuvlKRs/j5tnXgMBk1pzvGdxNsv07iw2MzptM0EUM=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsTSu54NttfCVQ/cqA3k9X99ob1bhL/Jh+i6nSxF/ga5x2EKoL+idtdLlsc4Fge+q3CO5/dq/TeKDKhtNiOyICE0HF+6lwCPRcxQtHrd2JYHatVQPlVXpl50UTqHMyjbFOA/6OHvgGioKpR3IJs2Pz4ap/5iqJpoDHD4GVwX0plZ8qy94iv5nkq/t236GgJ/y8bVkuD7ftWNskTds6tW0RyjPXfF/WJ8E+cNpyi75Z6YXGNE8p4Gdg8UcK/GTnf4tYs6WdFMn+oopQIu8TAZMpmiltVZJB67oNN/jbrcOGhKbb/TCT+9hFqHWsyEhHdd9xvYdh4oWin0rpgozM7ljLQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
    public static String notify_url = "http://119.29.94.246:8080/tenancy/p/alipaySuccess";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
    public static String return_url = "http://119.29.94.246:8080/tenancy/p/alipaySuccess";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

   //
//    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

//    /**
//     * 写日志
//     * @param sWord 要写入日志里的文本内容
//     */
//    public static void logResult(String sWord) {
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
//            writer.write(sWord);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}

