package com.example.jsconsumerdeposit.service.impl;

import com.example.jsconsumerdeposit.service.LoginService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
@Component
public class LoginServiceImpl implements LoginService {
    @Override
    public String ss(Double balance, String banknumber) {
        return "访问出错，请重试！";
    }

    @Override
    public String read(HttpServletResponse response, Integer uid) {
        return "访问出错，请重试！";
    }
}
