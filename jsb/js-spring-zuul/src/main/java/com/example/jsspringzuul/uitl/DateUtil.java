package com.example.jsspringzuul.uitl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    //获取时间相减天数的方法
    public  static  long getDaySub(String beginDatestr,String engDatestr){
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date begindate;
        Date endDate;
        try {
            //parse方法将String转为date
            begindate = format.parse(beginDatestr);
            endDate = format.parse(engDatestr);
            day = (endDate.getTime() - begindate.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  day;
    }
    //获取当前时间的方法
    public static  String getNowDate(){
        String NowTime = "";
        Date dt = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        //SimpleDateFormat CeshiFmt1=new SimpleDateFormat("yyyy/MM/dd HH:mm");
        //SimpleDateFormat CeshiFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //SimpleDateFormat CeshiFmt3=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        //SimpleDateFormat CeshiFmt4=new SimpleDateFormat("yyyy/MM/dd E");
        //SimpleDateFormat CeshiFmt5=new SimpleDateFormat(
                //"一年中的第 D 天 ，第w个星期 ，一个月中第W个星期 ，k时 z时区");
        //format方法将date转为String
        NowTime = sd.format(dt);
        return NowTime;
    }
    //date转String方法
    public static  String DchangeS(Date d){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        return  sd.format(d);
    }
    //String 转date
    public static  Date SchangeD(String s){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d =  sd.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 判断两个日期是否为同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }
    //得到几个月后的日期
    public static  Date getfuture(Date date,int month){
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH,month);//在日历的月份上增加month个月
        return c.getTime();
    }

}
