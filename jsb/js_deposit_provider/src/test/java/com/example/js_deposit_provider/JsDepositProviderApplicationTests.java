package com.example.js_deposit_provider;

import com.example.js_deposit_provider.dao.DepositFixationDao;
import com.example.js_deposit_provider.entity.DepositFixation;
import com.example.js_deposit_provider.util.DateUtil;
import com.example.js_deposit_provider.util.IndustrySMS;
import com.example.js_deposit_provider.util.OrderNumberUtil;
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
    @Resource
    private  DepositFixationDao depositFixationDao;
    @Test
    public void contextLoads() {
        String nowDate = DateUtil.getNowDate();
        List<DepositFixation> all = depositFixationDao.getAll(nowDate);
        for (DepositFixation d: all) {
            System.out.println(d.getDepositFixationid());
        }
    }

}
