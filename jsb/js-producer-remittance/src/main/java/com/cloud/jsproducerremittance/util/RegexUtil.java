package com.cloud.jsproducerremittance.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @作者 Goofy
 * @邮件 252878950@qq.com
 * @日期 2014-1-25
 * @描述 常用的正则表达式
 */
public class RegexUtil {

    /**
     * 判断卡号位数是否通过
     * @param card
     * @return boolean true,通过，false，没通过
     */
    public static boolean isIp(String card) {
        if (null == card || "".equals(card))
            return false;
        String regex = "^\\d{15}$";
        return card.matches(regex);
    }

    /**
     * 判断是否是正确的邮箱地址
     * @param email
     * @return boolean true,通过，false，没通过
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }

    /**
     * 判断是否含有中文
     * @param text
     * @return boolean true,通过，false，没通过
     */
    public static boolean isChinese(String text) {
        if (null == text || "".equals(text)) return false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(text);
        return m.find();
    }

    /**
     * 判断是否正整数
     * @param number 数字
     * @return boolean true,通过，false，没通过
     */
    public static boolean isNumber(String number) {
        if (null == number || "".equals(number)) return false;
        String regex = "[0-9]*";
        return number.matches(regex);
    }

    /**
     * 判断几位小数(正数)
     * @param decimal 数字
     * @param count 小数位数
     * @return boolean true,通过，false，没通过
     */
    public static boolean isDecimal(String decimal, int count) {
        if (null == decimal || "".equals(decimal)) return false;
        String regex = "^(-)?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){" + count+ "})?$";
        return decimal.matches(regex);
    }

    /**
     * 判断是否是手机号码
     * @param phoneNumber 手机号码
     * @return boolean true,通过，false，没通过
     */
    public static boolean isPhoneNumber(String phoneNumber){
        if (null == phoneNumber || "".equals(phoneNumber)) return false;
        String regex = "^1[3|4|5|8][0-9]\\d{8}$";
        return phoneNumber.matches(regex);
    }

    /**
     * 判断是否含有特殊字符
     * @param text
     * @return boolean true,通过，false，没通过
     */
    public static boolean hasSpecialChar(String text){
        if (null == text || "".equals(text)) return false;
        if(text.replaceAll("[a-z]*[A-Z]*\\d*-*_*\\s*", "").length()==0){
            //如果不包含特殊字符
            return true;
        }
        return false;
    }

}
