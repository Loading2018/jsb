package com.cloud.jsproducerremittance.SMS;

import java.net.URLEncoder;
import java.util.Random;

public class IndustrySMS {
    private static String operation = "/industrySMS/sendSMS";
    private static String accountSid = Config.ACCOUNT_SID;
    private static String templateid= "926452023";
    private static String param = new Random().nextInt(1000000) + ",5";
    /**
     * 验证码通知短信
     */
    public static String execute(String to)
    {
        String tmpSmsContent = null;
        try{
            tmpSmsContent = URLEncoder.encode(templateid, "UTF-8");
        }catch(Exception e){

        }
        String url = Config.BASE_URL + operation;
        String body = "accountSid=" + accountSid + "&to=" + to + "&templateid="+ templateid+HttpUtil.createCommonParam() +
                 "&param="+param ;

        // 提交请求
        String result = HttpUtil.post(url, body);
        System.out.println("result:" + System.lineSeparator() + result);
        return param;
    }
}
