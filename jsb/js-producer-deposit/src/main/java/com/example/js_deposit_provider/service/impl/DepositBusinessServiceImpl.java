package com.example.js_deposit_provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.js_deposit_provider.entity.DepositBusiness;
import com.example.js_deposit_provider.dao.DepositBusinessDao;
import com.example.js_deposit_provider.service.DepositBusinessService;
import com.example.js_deposit_provider.util.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * (DepositBusiness)表服务实现类
 *
 * @author makejava
 * @since 2018-12-19 14:45:32
 */
@Service("depositBusinessService")
public class DepositBusinessServiceImpl implements DepositBusinessService {
    @Resource
    private DepositBusinessDao depositBusinessDao;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 通过ID查询单条数据
     *
     * @param depositBusinessid 主键
     * @return 实例对象
     */
    @Override
    public DepositBusiness queryById(Integer depositBusinessid) {
        return this.depositBusinessDao.queryById(depositBusinessid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DepositBusiness> queryAllByLimit(int offset, int limit) {
        return this.depositBusinessDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param depositBusiness 实例对象
     * @return 实例对象
     */
    @Override
    public DepositBusiness insert(DepositBusiness depositBusiness) {
        this.depositBusinessDao.insert(depositBusiness);
        return depositBusiness;
    }

    /**
     * 修改数据
     *
     * @param depositBusiness 实例对象
     * @return 实例对象
     */
    @Override
    public DepositBusiness update(DepositBusiness depositBusiness) {
        this.depositBusinessDao.update(depositBusiness);
        return this.queryById(depositBusiness.getDepositBusinessid());
    }

    /**
     * 通过主键删除数据
     *
     * @param depositBusinessid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer depositBusinessid) {
        return this.depositBusinessDao.deleteById(depositBusinessid) > 0;
    }

    /**
     * 查询所有存款业务信息并放入redis
     */
    @Override
    public void getAllBusinessInfo() {
        List<DepositBusiness> allBussinessInfo = depositBusinessDao.getAllBussinessInfo();
        Map<Object,Object> map1 = new HashMap<Object,Object>();
        Map<Object,Object> map2 = new HashMap<Object,Object>();
        Map<Object,Object> map3 = new HashMap<Object,Object>();
        for (DepositBusiness d:allBussinessInfo) {
            if ("1".equals(d.getDepositBusinesstype())){
                map1.put(d.getDepositBusinessid().toString(),d);
                redisUtil.hmset("1",map1);
            }
            if ("2".equals(d.getDepositBusinesstype())){
                map2.put(d.getDepositBusinessid().toString(),d);
                redisUtil.hmset("2",map2);
            }
            if ("3".equals(d.getDepositBusinesstype())){
                map3.put(d.getDepositBusinessid().toString(),d);
                redisUtil.hmset("3",map3);
            }
        }
    }

    /**
     * 根据类型ID获得业务信息
     * @param TypeID
     * @return
     */
    @Override
    public String getInfoByTypeId(String TypeID) {
        List<DepositBusiness> dblist = new ArrayList<DepositBusiness>();
        try{
            if (TypeID != null && TypeID !=""){
                Map<Object, Object> hmget = redisUtil.hmget(TypeID);
                Set<Object> objects = hmget.keySet();
                for (Object o:objects) {
                    DepositBusiness d = (DepositBusiness)hmget.get(o);
                    dblist.add(d);
                }
                return JSON.toJSONString(dblist);
            }else {
                return "404";
            }
        }catch (Exception e){
            return "404";
        }

    }

    /**
     * 根据业务ID获取业务信息
     * @param DepositID
     * @return
     */
    @Override
    public String getInfoByDepositID(String DepositID) {
        int id = Integer.parseInt(DepositID);
        DepositBusiness infoByDepositBusinessID = depositBusinessDao.getInfoByDepositBusinessID(id);
        return JSON.toJSONString(infoByDepositBusinessID);
    }

    @Override
    public Double getLvByDepositID(String DepositID) {
        int id = Integer.parseInt(DepositID);
        DepositBusiness lvByID = depositBusinessDao.getLvByDepositBusinessID(id);
        String depositBusinessrate = lvByID.getDepositBusinessrate();
        double lv = Double.parseDouble(depositBusinessrate);
        return lv;
    }

    @Override
    public String updateLvByDepositID(String DepositID) {
        int id = Integer.parseInt(DepositID);
        DepositBusiness depositBusiness = depositBusinessDao.updateLvByDepositBusinessID(id);
        if (depositBusiness != null){
            return JSON.toJSONString("修改成功");
        }else {
            return JSON.toJSONString("修改失败");
        }
    }
}