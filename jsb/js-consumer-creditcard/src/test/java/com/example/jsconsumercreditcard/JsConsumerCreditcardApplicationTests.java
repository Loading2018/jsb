package com.example.jsconsumercreditcard;

import com.example.jsconsumercreditcard.phoneutil.IndustrySMS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsConsumerCreditcardApplicationTests {

    @Test
    public void contextLoads() {
        IndustrySMS.execute("18234184482");
    }

}
