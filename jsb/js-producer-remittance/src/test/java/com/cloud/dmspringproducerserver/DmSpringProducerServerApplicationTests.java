package com.cloud.dmspringproducerserver;

import com.alibaba.fastjson.JSON;
import com.cloud.jsproducerremittance.entity.Batch;
import com.cloud.jsproducerremittance.rabbitmqUitl.RabbitConfig;
import com.cloud.jsproducerremittance.service.impl.BatchServiceImpl;
import com.cloud.jsproducerremittance.service.impl.BranchServiceImpl;
import com.cloud.jsproducerremittance.uitl.ExcelUtil;
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

        /*BufferedWriter bw = new BufferedWriter(new FileWriter("D://data.txt"));
        FileWriter fw = new FileWriter("D://data.txt",true);
        //时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        //设置文本内容
        StringBuilder sb=new StringBuilder();
        sb.append(format + "\r\n");
        sb.append("123" + "321" + "\r\n");
        sb.append("456" + "456" + "\r\n");
        sb.append("789" + "789" + "\r\n");
        String a = sb.toString();
        byte[] b = a.getBytes();
        fw.write(a);
        fw.close();*/

        //获取配置文件 读取数据
        /*InputStream in = DmSpringProducerServerApplicationTests.class.getClassLoader().getResourceAsStream("database.properties");
        Properties pro = new Properties();
        pro.load(in);
        String property = pro.getProperty("10");
        String gbk = new String(property.getBytes("iso8859-1"), "GBK");
        System.out.println(gbk);*/



       /* List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("U_ID", "123");
        map.put("UDT_STATE","456");





        result.add(map);

        String fileName = "公司员工信息表.xls";        // 定义文件名
        String headString = "公司员工信息表";          // 定义表格标题
        String sheetName = "工作表一";                  // 定义工作表表名
        String filePath = "E:\\test\\";             // 文件本地保存路径
        String[] thead = {"主键(U_ID)","状态(UDT_STATE)","姓名(U_NAME)","SAP编号(UDT_SAPID)","性别(U_SEX)","管理关系归属(UDT_ASCRIPTION)","所属公司(UDT_COMPANY)","一级部门(UDT_FIRST)","二级部门(UDT_SECOND)","职务岗位(POSSITION_ID)","厂区(UDT_PARK)","城市(CITY_NAME)"};                    // 定义表头内容
        int[] sheetWidth = {2500,3500,3000,4000,2500,5000,5000,5000,5000,5000,3500,3500};   // 定义每一列宽度

        HSSFWorkbook wb = new HSSFWorkbook();           // 创建Excel文档对象
        HSSFSheet sheet = wb.createSheet(sheetName);    // 创建工作表

        // ①创建表格标题
        ExcelUtil.createHeadTittle(wb, sheet, headString, result.get(0).size() - 1);

        // result.get(0).size() - 1为表格占用列数，从0开始
        // ②创建表头
        ExcelUtil.createThead(wb, sheet, thead, sheetWidth);
        // ③填入数据
        ExcelUtil.createTable(wb, sheet, result);
        FileOutputStream fos = new FileOutputStream(new File(filePath+fileName));
        // filePath,fileName是如上定义的文件保存路径及文件名
        wb.write(fos);
        fos.close();
        wb.close();*/

       /*String a = "1";
       String b = "1.11";
        double v = Double.parseDouble(a);
        System.out.println(v);
        System.out.println(v == 1);
        double v1 = Double.parseDouble(b);
        System.out.println(v1);*/

//        System.out.println(40.01 > 40);
        /*String a = "6212260502017442620";
        String substring = a.substring(a.length() - 4);
        System.out.println(substring);*/

        /*long now = System.currentTimeMillis();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
        String time=dateFormat.format(now);
        System.out.println(now);
        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
        System.out.println(yyyyMMdd);*/

      /*  int a = 6;
       // 彩票
        Integer[] b = new Integer[6];
        int c = 0;
        while (true){
            List<Integer> list = Arrays.asList(b);
            int i = (int)(Math.random()*100);
            if (i < 33 && i > 0){
                boolean contains = list.contains(i);
                if (!contains){
                    b[c] = i;
                    a --;
                    System.out.println(i);
                }
            }
            if (a == 0){
                break;
            }
        }
        while (true){
            int i = (int)(Math.random()*100);
            if (i < 16 && i > 0){
                System.out.println("蓝号"+i);
                break;
            }
        }*/

                /* Date d = new Date();
                 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 Calendar ca = Calendar.getInstance();
                 ca.add(Calendar.DATE, 3);// num为增加的天数，可以改变的
                 d = ca.getTime();
                 String enddate = format.format(d);
                 System.out.println("增加天数以后的日期：" + enddate);*/


        /*Batch batch = new Batch();
        try{        //判断对象的属性是否为空
        for(Field f : batch.getClass().getDeclaredFields()){
            f.setAccessible(true);
            //log.info(f.getName());
            if ("".equals(f.get(batch))){
                System.out.println("空字符串");
            }
            if(f.get(batch) == null){
                System.out.println("null");
            }
        }
        }catch (IllegalAccessException e){
            System.out.println("异常");
        }*/
        /*InputStream in = BranchServiceImpl.class.getClassLoader().getResourceAsStream("branch.properties");
        Properties pro = new Properties();
        pro.load(in);
        String property = pro.getProperty(1 + "");
        String currency = new String(property.getBytes("iso8859-1"), "GBK");
        System.out.println(currency);
        String[] split = currency.split(",");
        System.out.println(split[0]);*/

    }

}
