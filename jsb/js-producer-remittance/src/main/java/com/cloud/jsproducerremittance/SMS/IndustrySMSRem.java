package com.cloud.jsproducerremittance.SMS;

import java.net.URLEncoder;

public class IndustrySMSRem {
    private static String operation = "/industrySMS/sendSMS";
    private static String accountSid = Config.ACCOUNT_SID;
    private static String templateid= "959342749";
//    private static String param = new Random().nextInt(1000000) + ",5";
    /**
     * 汇款失败通知短信
     */
    public static void execute(String to, String param) {
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
    }
}
