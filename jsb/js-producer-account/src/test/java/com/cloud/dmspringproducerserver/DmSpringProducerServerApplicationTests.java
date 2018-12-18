package com.cloud.dmspringproducerserver;

import com.cloud.jsproduceraccount.SMS.IndustrySMS;
import com.cloud.jsproduceraccount.Voice.VoiceCode;
import com.cloud.jsproduceraccount.uitl.RandomCreditCardNumberGeneratorss;
import com.cloud.jsproduceraccount.uitl.RedisUtil;
import io.netty.handler.codec.http.HttpMethod;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DmSpringProducerServerApplicationTests {

    private  static  Double a = 0.0;
   /*public static final String PREFIX = "";
    private static long code;
    *//*public static void main(String[] args) {
        System.out.println(nextCode());
        System.out.println(nextCode());
        System.out.println(nextCode());
    }*//*
    public static synchronized String nextCode() {
        code++;
        String str = new SimpleDateFormat("yyyyMM").format(new Date());
        Long m = Long.parseLong((str)) * 10000000000000L;
        m += 1234567;
//        m += code;
        return PREFIX + m;
    }*/
    /*public static void main(String[] args)
    {
        int[] arr = new int[10];

        for (int i = 0; i < 10; i++)
        {
            arr[i] = (int) (Math.random() * 40) + 1;
            for (int j = 0; j < i; j++)
            {
                if (arr[j] == arr[i])
                {
                    i--;
                    break;
                }
            }
        }
        for (int i = 0; i < 10; i++)
            System.out.print(arr[i] + " ");
    }*/
    /*public static String game(int count){
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(game(7));
    }*/
 /*   public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<7;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        code++;
        String stro = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        Long m = Long.parseLong((stro)) * 10000000000000L;
//        Long l = Long.parseLong(sb.toString());
//        Long ll = m + l;

//        System.out.println(ll);
//        String i = String.valueOf(ll);
        System.out.println(stro);
    }*/

    public static void main(String[] args) throws ParseException {
            /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date tmpDate = format.parse("2018-11-12");

            Calendar cal = new GregorianCalendar();

            cal.set(tmpDate.getYear(), tmpDate.getMonth(), tmpDate.getDay());
            System.out.println(cal.get(Calendar.DAY_OF_WEEK) - 1);*/

        /*SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse("2018年11月10日"));
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
            System.out.println(dayForWeek);
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
            System.out.println(dayForWeek);
        }*/

        /*String[] str={"q","w"};
        String s="q";

        System.out.println(Arrays.asList(str));//先将数组转换为List
        System.out.println(Arrays.asList(str).contains(s));
        Set<String> set = new HashSet<>(Arrays.asList(str));
        System.out.println(set.contains(s));*/

        /*String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(System.currentTimeMillis());
        Date date = new Date();
        System.out.println(date.getTime());*/

        /*String execute = IndustrySMS.execute("18634399255");
        System.out.println(execute);*/
//        String execute = VoiceCode.execute("0");

        /*RandomCreditCardNumberGeneratorss ran = new RandomCreditCardNumberGeneratorss();
        String[] main = ran.main();
        System.out.println(main);*/

        /*String name = HttpMethod.POST.name();
        System.out.println(name);*/
//        int i = new Random().nextInt(1000000);
//        System.out.println(i);

        /*Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher("123");
        System.out.println(isNum.matches());*/

      /*  Pattern PATTERN = Pattern.compile("0|([-]?[1-9][0-9]*)");
        boolean matches = PATTERN.matcher("123.00").matches();
        System.out.println(matches);
        Pattern pattern = Pattern.compile("^[-\\+]?\\d+(\\.\\d*)?|\\.\\d+$");
        boolean matches2 = pattern.matcher("123.00").matches();
        System.out.println(matches2);*/
        System.out.println(a);
    }
}
