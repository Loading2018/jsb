package com.example.js_deposit_provider;


import com.example.js_deposit_provider.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsDepositProviderApplicationTests {

    public static void main(String[] args) {
        String nowDate = DateUtil.getNowDate();
        System.out.println(nowDate);
        String outtime = "2019-01-06";
        String outtime1 = "2020-01-06";
        long daySub = DateUtil.getDaySub(nowDate, outtime);
        long daySub1 = DateUtil.getDaySub(nowDate, outtime1);
        System.out.println(daySub);
        System.out.println(daySub1);
        boolean a = daySub1>daySub;
        System.out.println(a);
    }
    @Test
    public void contextLoads() {

    }

}
