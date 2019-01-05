package com.example.jsconsumerdeposit.service;

import com.example.jsconsumerdeposit.service.impl.DepositServiceImpl;
import com.example.jsconsumerdeposit.service.impl.LoginServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
@FeignClient(name = "js-producer-logintest",fallback = LoginServiceImpl.class)
public interface LoginService {
    /*
   扣款
    */
    @RequestMapping(value = "/upbalance",method =RequestMethod.POST)
    String ss(@RequestParam("balance") Double balance, @RequestParam("banknumber") String banknumber);

    /**
     * 查用户信息
     * @param
     * @return
     */
    @RequestMapping(value = "/Read",method =RequestMethod.POST)
    String read(HttpServletResponse response, @RequestParam("te")Integer uid);
}
