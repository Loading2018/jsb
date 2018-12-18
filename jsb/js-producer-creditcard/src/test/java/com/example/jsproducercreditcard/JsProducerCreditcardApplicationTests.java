package com.example.jsproducercreditcard;

import com.example.jsproducercreditcard.phoneutil.IndustrySMS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsProducerCreditcardApplicationTests {

    @Test
    public void contextLoads() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sf.format(date);

        System.out.println("-----------------------------------------");
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, +30);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
        System.out.println(endDate);

        IndustrySMS.execute("18234184482");
    }

}
