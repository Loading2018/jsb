package com.cloud.dmspringproducerserver;

import com.alibaba.fastjson.JSON;
import com.cloud.jsproducerremittance.entity.Batch;

import com.cloud.jsproducerremittance.service.impl.BatchServiceImpl;
import com.cloud.jsproducerremittance.service.impl.BranchServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DmSpringProducerServerApplicationTests {

    public static void main(String[] args) throws IOException {

        String a = "A,B,C,DEFG";
        String[] split = a.split(",");
        System.out.println(split[0]);
        System.out.println(split[1]);
        System.out.println(split[2]);
        System.out.println(split[3]);
    }

}
