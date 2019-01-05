package com.cloud.jsproducerremittance.service.impl;

import com.alibaba.fastjson.JSON;
import com.cloud.jsproducerremittance.SMS.IndustrySMSRem;
import com.cloud.jsproducerremittance.entity.Remittancetransaction;
import com.cloud.jsproducerremittance.dao.RemittancetransactionDao;
import com.cloud.jsproducerremittance.entity.Remittansel;
import com.cloud.jsproducerremittance.entity.Remittanvalue;
import com.cloud.jsproducerremittance.entity.Remvalue;
import com.cloud.jsproducerremittance.service.RemittancetransactionService;
import com.cloud.jsproducerremittance.util.GetBank;
import com.cloud.jsproducerremittance.util.RabbitConfig;
import com.cloud.jsproducerremittance.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * (Remittancetransaction)表服务实现类
 *
 * @author makejava
 * @since 2018-12-24 16:21:31
 */
@Service("remittancetransactionService")
public class RemittancetransactionServiceImpl implements RemittancetransactionService {
    @Resource
    private RemittancetransactionDao remittancetransactionDao;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 通过ID查询单条数据
     *
     * @param remittancetransactionId 主键
     * @return 实例对象
     */
    @Override
    public Remittancetransaction queryById(Integer remittancetransactionId) {
        return this.remittancetransactionDao.queryById(remittancetransactionId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Remittancetransaction> queryAllByLimit(int offset, int limit) {
        return this.remittancetransactionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param rm 实例对象
     * @return 实例对象
     */
    @Override
    public String insert(Remittancetransaction rm) {
        if (rm.getRemittancetransactionNumber().equals(rm.getRemittancetransactionCardnumber())) {
            return "收款卡号和付款卡号不能相同!";
        }
        //判断是否是汉字  收款人姓名
        boolean Name = rm.getRemittancetransactionName().matches("[\\u4e00-\\u9fa5]+");
        //判断是否是银行卡号 收款卡号
        boolean cardNo = GetBank.checkBankCard(rm.getRemittancetransactionCardnumber());
        //判断输入的金额是否是int类型
        Pattern PATTERN = Pattern.compile("0|([-]?[1-9][0-9]*)");
        boolean intt = PATTERN.matcher(rm.getRemittancetransactionPrice()).matches();
        //判断输入的金额是否是double类
        Pattern pattern = Pattern.compile("^[-\\+]?\\d+(\\.\\d*)?|\\.\\d+$");
        boolean doublee = pattern.matcher(rm.getRemittancetransactionPrice()).matches();
        if (Name && cardNo && (intt || doublee)) {
            double v = Double.parseDouble(rm.getRemittancetransactionPrice());
            if (v > 20000) {
                return "单笔汇款，单笔仅支持20000.00";
            }
            //付款卡号 限制每日汇款总额度
            String num = rm.getRemittancetransactionNumber();
            String price = rm.getRemittancetransactionPrice();
            if (redisUtil.get("remitt" + num) == null) {
                redisUtil.set("remitt" + num, price, 30);
            } else {
                Double doo = Double.parseDouble(redisUtil.get("remitt" + num).toString());
                long expire = redisUtil.getExpire("remitt" + num);
                Double a = doo + Double.parseDouble(price);
                if (a > 40000) {
                    return "今日汇款已限额!最多累加40000.00!";
                }
                redisUtil.set("remitt" + num, a.toString(), expire);
            }
            //汇款发起时间
            rm.setRemittancetransactionCurrent(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            //将汇款信息放入到redis中
            String string = JSON.toJSONString(rm);
            redisUtil.hset("remit", rm.getRemittancetransactionPayuserid() + "", string, 60 * 5);
            return "200输入正确已发送验证码";
        }
        return "400请正确输入";
    }

    /**
     * 修改数据
     *
     * @param remittancetransaction 实例对象
     * @return 实例对象
     */
    @Override
    public Remittancetransaction update(Remittancetransaction remittancetransaction) {
        this.remittancetransactionDao.update(remittancetransaction);
        return this.queryById(remittancetransaction.getRemittancetransactionId());
    }

    /**
     * 通过主键删除数据
     *
     * @param remittancetransactionId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer remittancetransactionId) {
        return this.remittancetransactionDao.deleteById(remittancetransactionId) > 0;
    }

    //汇款发送验证码  验证验证码 添加汇款交易表 和 发送MQ
    @Override
    public String sendSMS(Remittanvalue remi) {
        //根据Userid将汇款信息从redis拿出来
        String o = (String) redisUtil.hget("remit",remi.getUserid());
        String au = (String) redisUtil.hget("rem",remi.getPhone());
        if (o == null || au == null){
            return "操作超时!请重新操作!";
        }
        redisUtil.hdel("rem",remi.getPhone());
        //反序列化成对象
        Remittancetransaction re = JSON.parseObject(o, Remittancetransaction.class);
        if (au != null && au.equals(remi.getAuth())){
            //发送卡号和名字给瀚文验证
            String NumName = re.getRemittancetransactionCardnumber() + "," + re.getRemittancetransactionName() + "," +
                    re.getRemittancetransactionPayuserid() + "," + re.getPhone();

            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_SELNUMBER_NAME,NumName);
        }
        return "验证码错误";
    }


    //监听验证卡号姓名后的队列
    @RabbitListener(queues = RabbitConfig.QUEUE_HANWEN)
    public String QUEUEVoic(String msg){
        //返回000则是不匹配 汇款失败 短信通知   接收 验证后状态码0 收款卡号1 姓名2 付款人ID3 付款人电话4
        String[] split = msg.split(",");
        if ("000".equals(split[0])){
            IndustrySMSRem.execute(split[4],"****"+split[1].substring(split[1].length() - 4) + "," + split[2]);
            System.out.println("短信通知汇款人，汇款失败原因。");
            redisUtil.hdel("remit",split[3]);
            return "400";
        }
        String o = (String) redisUtil.hget("remit",split[3]);
        redisUtil.hdel("remit",split[3]);
        Remittancetransaction re = JSON.parseObject(o, Remittancetransaction.class);
        Integer ba = re.getRemittancetransactionBlank();
        int insert = 0;
        Double dd = 0.00;
        if (ba == 3){
            //添加汇款加以表 本行不付手续费
            re.setRemittancetransactionProcedure("0");
            insert = this.remittancetransactionDao.insert(re);
        }else {
            dd = Double.parseDouble(re.getRemittancetransactionPrice()) * 0.01;
            if (dd > 50) {
                dd = 50.00;
            }else if (dd < 2){
                dd = 2.00;
            }
            re.setRemittancetransactionProcedure(dd.toString());
            insert = this.remittancetransactionDao.insert(re);
        }
        if (insert > 0) {
            //调MQ发送汇款 收款卡号 付款卡号 交易金额 手续费
            String json = re.getRemittancetransactionCardnumber() + ","
                    + re.getRemittancetransactionNumber() + ","
                    + re.getRemittancetransactionPrice()  + ","
                    + dd ;
            //生成汇款文件
            try {
                rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_REMITTANCE, json);
                //fw 中的true和false是设置写入文本的数据是否覆盖原来的，不覆盖设置 false 追加在后面
                FileWriter fw = new FileWriter("D://remittan.txt",false);
                //时间
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(date);
                //设置文本内容
                StringBuilder sb = new StringBuilder();
                sb.append("当前时间"+format + "\r\n");
                sb.append("收款人姓名:\r\t" + re.getRemittancetransactionName() + "\r\n");
                sb.append("收款卡号:\r\t" + re.getRemittancetransactionCardnumber() + "\r\n");
                //获取配置文件 读取数据
                InputStream in = RemittancetransactionServiceImpl.class.getClassLoader().getResourceAsStream("database.properties");
                Properties pro = new Properties();
                pro.load(in);
                String property = pro.getProperty(re.getRemittancetransactionBlank() + "");
                String gbk = new String(property.getBytes("iso8859-1"), "GBK");
                sb.append("收款银行:" + gbk + "\r\n");
                sb.append("汇款金额:" + re.getRemittancetransactionPrice() + "\r\n");
                sb.append("手续费:" + dd + "\r\n");
                //
                InputStream in1 = RemittancetransactionServiceImpl.class.getClassLoader().getResourceAsStream("time.properties");
                pro.load(in1);
                String property1 = pro.getProperty(re.getRemittancetransactionTime() + "");
                String time = new String(property1.getBytes("iso8859-1"), "GBK");
                sb.append("汇款时间:" + time + "\r\n");
                sb.append("汇款时间:" + re.getRemittancetransactionCurrent() + "\r\n");
                sb.append("付款卡号:" + re.getRemittancetransactionNumber() + "\r\n");
                String a = sb.toString();
                fw.write(a);
                fw.close();
            }catch (IOException e){
                e.printStackTrace();
                return "生成纸质汇款单失效";
            }
            return "汇款成功" + re.getRemittancetransactionPrice() + "元";
        }
        return "汇款失败";
    }

    @Override
    public String selALL(Remittansel rm) {
        if (rm.getType() == 0){
            List<Remittancetransaction> remittancetransactions = this.remittancetransactionDao.queryAll("",rm.getCardnumber(), rm.getOnetime(), rm.getTwotime());
            System.out.println("000");
            System.out.println(JSON.toJSONString(remittancetransactions));
            PageHelper.startPage(rm.getIndex(),rm.getPageSize());
            PageInfo<Remittancetransaction> p = new PageInfo<>(remittancetransactions);
            return JSON.toJSONString(p);
        }
        List<Remittancetransaction> remittancetransactions = this.remittancetransactionDao.queryAll(rm.getCardnumber(), "", rm.getOnetime(), rm.getTwotime());
        PageHelper.startPage(rm.getIndex(),rm.getPageSize());
        PageInfo<Remittancetransaction> p = new PageInfo<>(remittancetransactions);
        return JSON.toJSONString(p);
    }

    @Override
    public String selone(Integer id) {
        try {
            Remittancetransaction queryone = this.remittancetransactionDao.getRMInfoByID(id);
            //汇款时间方式
            InputStream in1 = RemittancetransactionServiceImpl.class.getClassLoader().getResourceAsStream("time.properties");
            Properties pro = new Properties();
            pro.load(in1);
            String property1 = pro.getProperty(queryone.getRemittancetransactionTime() + "");
            String time = new String(property1.getBytes("iso8859-1"), "GBK");
            //汇款银行
            InputStream in = RemittancetransactionServiceImpl.class.getClassLoader().getResourceAsStream("database.properties");
            pro.load(in);
            String property = pro.getProperty(queryone.getRemittancetransactionBlank() + "");
            String gbk = new String(property.getBytes("iso8859-1"), "GBK");

            Remvalue remvalue = new Remvalue();
            remvalue.setRemittancetransactionBlank(gbk);
            remvalue.setRemittancetransactionCardnumber(queryone.getRemittancetransactionCardnumber());
            remvalue.setRemittancetransactionCurrent(queryone.getRemittancetransactionCurrent());
            remvalue.setRemittancetransactionName(queryone.getRemittancetransactionName());
            remvalue.setRemittancetransactionPayuserid(queryone.getRemittancetransactionPayuserid());
            remvalue.setRemittancetransactionPrice(queryone.getRemittancetransactionPrice());
            remvalue.setRemittancetransactionTime(time);
            remvalue.setRemittancetransactionNumber(queryone.getRemittancetransactionNumber());
            return JSON.toJSONString(remvalue);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}