package com.example.js_deposit_provider.util;

import java.util.Calendar;
import java.util.Date;

public class TimeAlgorithmUtil {

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Integer differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //不同年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else    //同一年
        {
            return day2 - day1;
        }
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Integer differentMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        System.out.println(day1);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        System.out.println(day2);

        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        int day = day1-day2;
        day = day < 0?-day:day;
        if (year1 != year2) {
            day += ((year1 - year2) * 12 + (month1 - month2)) * 30;
        } else {
            Integer month = month1 - month2;
            month = month < 0 ? -month : month;
            day += month * 30;
        }
        return day;
    }

    /**
     * 到期时间
     *
     * @param date                存款时间
     * @param depositBusinessTime 存款时长
     * @return
     */
    public static Date expirationDate(Date date, Integer depositBusinessTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (depositBusinessTime >= 30) {
            calendar.add(Calendar.MONTH, depositBusinessTime/30);
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, depositBusinessTime);
        }
        return calendar.getTime();
    }
}
