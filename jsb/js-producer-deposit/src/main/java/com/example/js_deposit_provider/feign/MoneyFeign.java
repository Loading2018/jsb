package com.example.js_deposit_provider.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@FeignClient(name = "js-producer-logintest")
public interface MoneyFeign {
    /**
     * 扣钱
     * @param balance
     * @param id
     * @return
     */
    @RequestMapping(value = "/upbalance",method =RequestMethod.POST)
    String ss(@RequestParam("balance") Double balance,@RequestParam("idnumber")Integer id);

    /**
     * 查用户信息
     * @param uid
     * @return
     */
    @RequestMapping(value = "/Read",method =RequestMethod.POST)
    String read(@RequestParam("te")Integer uid);

    /**
     * 增款
     * @param balance
     * @param id
     * @return
     */
    @RequestMapping(value = "/insmoney",method =RequestMethod.POST)
    String insmoney(@RequestParam("balance") Double balance,@RequestParam("idnumber")Integer id);
}
