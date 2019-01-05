package com.cloud.jsproducerremittance.service.impl;

import com.alibaba.fastjson.JSON;
import com.cloud.jsproducerremittance.entity.Makeremittance;
import com.cloud.jsproducerremittance.dao.MakeremittanceDao;
import com.cloud.jsproducerremittance.service.MakeremittanceService;
import com.cloud.jsproducerremittance.util.GetBank;
import com.cloud.jsproducerremittance.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * (Makeremittance)表服务实现类
 *
 * @author makejava
 * @since 2018-12-24 16:20:37
 */
@Service("makeremittanceService")
public class MakeremittanceServiceImpl implements MakeremittanceService {
    @Resource
    private MakeremittanceDao makeremittanceDao;
    @Resource
    private RedisUtil redisUtil;



    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Makeremittance> queryAllByLimit(int offset, int limit) {
        return this.makeremittanceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *添加预约汇款信息
     * @param mm 实例对象
     * @return 实例对象
     */
    @Override
    public String insert(Makeremittance mm) {
        String mPrice = mm.getMakeremittancePrice();
        //判断输入的金额是否是int类型
        Pattern PATTERN = Pattern.compile("0|([-]?[1-9][0-9]*)");
        boolean intt = PATTERN.matcher(mPrice).matches();
        //判断输入的金额是否是double类型
        Pattern pattern = Pattern.compile("^[-\\+]?\\d+(\\.\\d*)?|\\.\\d+$");
        boolean doublee = pattern.matcher(mPrice).matches();
        if (!(intt || doublee)){
            return "请输入正确的金额!";
        }
        double v = Double.parseDouble(mPrice);
        if (v<20000){
            return "预约汇款金额低于20000,不支持预约汇款!";
        }
        if (redisUtil.get("mak" + mm.getMakeremittancePayuserid()) == null){
            redisUtil.set("mak" + mm.getMakeremittancePayuserid(),"1",60*60*24);
        }else{
            Integer count = Integer.parseInt((String) redisUtil.get("mak" + mm.getMakeremittancePayuserid())) + 1;
            if (count >= 6){
                return "今日预约5次数已上限,如您还想预约请取消一项预约事项!";
            }
            long expire = redisUtil.getExpire("mak" + mm.getMakeremittancePayuserid());
            redisUtil.set("mak" + mm.getMakeremittancePayuserid(),count + "",expire);
        }
        //生成预约编号
        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String now = System.currentTimeMillis() + "";
        int i=(int)(Math.random()*900)+100;
        mm.setMakeremittanceSerialnumber(yyyyMMdd+now+i);
        //发起预约的当前时间
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        mm.setMakeremittanceData(format.format(d));
        String enddate = "到行可立即汇款!";
        //根据预约款的多少，系统决定预约时间
        if (v >= 50000 && v < 70000){
            ca.add(Calendar.DATE, 1);
            d = ca.getTime();
            enddate = format.format(d);
        }else if (v >= 70000 && v < 100000){
            ca.add(Calendar.DATE, 2);
            d = ca.getTime();
            enddate = format.format(d);
        } else if ( v >= 100000 && v < 500000) {
            ca.add(Calendar.DATE, 5);
            d = ca.getTime();
            enddate = format.format(d);
        }else if (v >= 500000){
            ca.add(Calendar.DATE, 7);
            d = ca.getTime();
            enddate = format.format(d);
        }
        mm.setMakeremittanceTime(enddate);
        mm.setMakeremittanceType(1);
        //判断是否是汉字  收款人姓名
        boolean hanzi = mm.getMakeremittanceName().matches("[\\u4e00-\\u9fa5]+");
        //判断是否是银行卡号 付款卡号 收款卡号
        boolean fukahao = GetBank.checkBankCard(mm.getMakeremittancePaynumber());
        boolean shoukahao = GetBank.checkBankCard(mm.getMakeremittanceNumber());

        if (!hanzi){
            return "请输入正确的姓名!";
        }
        if (!fukahao || !shoukahao){
            return "请输入正确的银行卡号!";
        }
        int insert = this.makeremittanceDao.insert(mm);
        if (insert > 0)
            return "200预约成功!";
        return "400预约失败!";
    }


    /**
     * 修改数据
     *
     * @param
     * @return
     */
    @Override
    public String update(String updatamaker) {
        String[] split = updatamaker.split(",");
        int update = this.makeremittanceDao.update(split[0], Integer.parseInt(split[1]));
        if (update > 0){
            Integer count = Integer.parseInt((String) redisUtil.get("mak" + split[1]));
            long expire = redisUtil.getExpire("mak" + split[1]);
            redisUtil.set("mak" + split[1], (count - 1) + "",expire);
            return "撤销成功!";
        }
        return "撤销失败!";
    }

    /**
     * 通过主键删除数据
     *
     * @param makeremittanceId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer makeremittanceId) {
        return this.makeremittanceDao.deleteById(makeremittanceId) > 0;
    }

    /**
     * 通过时间 用户ID 查询预约信息表
     *
     * @param selmaker
     * @return
     */
    @Override
    public String queryAll(String selmaker) {
        String[] split = selmaker.split(",");
        Map<Object, Object> hmget = redisUtil.hmget("makerBranchBranchMap");
        if (hmget.size() == 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("1", "晋商银行(胜利支行)");
            map.put("2", "晋商银行(大槐树支行)");
            map.put("3", "通达街支行");
            map.put("4", "南内环街支行");
            map.put("5", "恒大社区支行");
            map.put("6", "桥西支行");
            map.put("7", "双塔支行");
            map.put("8", "龙城支行");
            map.put("9", "亲贤北街支行");
            map.put("10", "小店支行");
            redisUtil.hmset("makerBranchBranchMap", map);
            hmget = redisUtil.hmget("makerBranchBranchMap");
        }
        List<Makeremittance> maker = this.makeremittanceDao.queryAll(split[0], split[1], Integer.parseInt(split[2]));
        List<Makeremittance> makelist = new ArrayList<>();
        for (Makeremittance xx : maker) {
            switch (xx.getMakeremittanceType()) {
                case 1:
                    xx.setType("<span style='color:yellow'>待审核</span>");
                    break;
                case 2:
                    xx.setType("<span style='color:green'>通过</span>");
                    break;
                case 3:
                    xx.setType("<span style='color:red'>取消</span>");
                    break;
            }
            String s = (String) hmget.get(xx.getMakeremittanceBranch() + "");
            xx.setBranchname(s);
            makelist.add(xx);
        }
        //分页
        PageHelper.startPage(Integer.parseInt(split[3]), Integer.parseInt(split[4]));
        PageInfo<Makeremittance> p = new PageInfo<>(makelist);
        return JSON.toJSONString(p);
    }

    /**
     * 根据预约编号 和用户ID
     * @param selonemaker
     * @return
     */
    @Override
    public String selonemaker(String selonemaker) {
        String[] split = selonemaker.split(",");  // 预约编号 用户ID
        Makeremittance mae = makeremittanceDao.queryByIdd(split[0], Integer.parseInt(split[1]));
        Object hmget = redisUtil.hmget("makerBranchBranchMap");
        Map<String,Object> mapp = (Map<String, Object>) hmget;
        switch (mae.getMakeremittanceType()){
            case 1:
                mae.setType("<span style='color:yello'>待审核</span>");
                break;
            case 2:
                mae.setType("<span style='color:green'>通过</span>");
                break;
            case 3:
                mae.setType("<span style='color:red'>取消</span>");
                break;
        }
        String s = (String) mapp.get(mae.getMakeremittanceBranch() + "");
        mae.setBranchname(s);
        return JSON.toJSONString(mae);
    }
}