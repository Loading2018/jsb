package com.example.jsspringzuul.filter;


import com.example.jsspringzuul.key.CommonConstants;

import com.example.jsspringzuul.uitl.CreatUtil;
import com.example.jsspringzuul.uitl.RedisUtil;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Base64;

@Component//组件化到spring
public class PreFilter extends ZuulFilter {

    @Resource
    private RedisUtil redisUtil;


    @Override
    public String filterType() {
        //过滤器类型 PRE ROUTE POST  这三个过滤器再执行过程中都有可能进入ERROR过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;//同一过滤器的优先级 优先级再高也不会超过上一级过滤器  越小优先级越高，可以为负数。
    }

    @Override
    public boolean shouldFilter() {

        /*//是否开启当前过滤器  一般不直接指定true 或 false
        //一般使用
        RequestContext ctx = RequestContext.getCurrentContext(); //这个对象是给过滤器传递信息的。
        ctx.setSendZuulResponse(true);  //设置布尔值
        boolean b = ctx.sendZuulResponse();  //获取布尔值
        //布尔值来判断
        return b;*/

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getRequestURL().toString().contains("login") || request.getRequestURL().toString().contains("zhuce")) {
            return false;
        }
        // TODO
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            return null;
        } else {
            if (StringUtils.isEmpty(token)) {
               ctx.setSendZuulResponse(false); //过滤该请求不对其进行路由
               ctx.setResponseBody("请求头错误");
               ctx.getResponse().setContentType("text/html;charset=utf-8");
               ctx.set("isSuccess",false);//传给后面两个网关
               return null;
            }
                byte[] secretKey = Base64.getEncoder().encode(CommonConstants.SECURITY_KEY.getBytes());
                Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
                String signature = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getSignature();
                if (claims != null) {
                    String s = CreatUtil.generateJwt(claims);
                    String signature1 = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(s).getSignature();
                    if(signature.equals(signature1)){
                        Integer user = (Integer) claims.get("user");
                        String UserNameToken = user + "token";
                        Long expirationDate = redisUtil.getExpire(UserNameToken);
                        //如果还剩余5分钟，重置redis里面数据的存活时间
                        if(expirationDate > 300){
                            redisUtil.expire(UserNameToken,1800);
                            return null;
                        }else {
                            ctx.setSendZuulResponse(false);
                            System.out.println("111111111111111111");//过滤该请求不对其进行路由
                            ctx.setResponseBody("登陆失效，请重新登陆");
                            ctx.set("isSuccess",false);//传给后面两个网关
                            return null;
                        }
                    }else {
                        ctx.setSendZuulResponse(false); //过滤该请求不对其进行路由
                        ctx.setResponseBody("登陆失效，清重新登陆");
                        ctx.set("isSuccess",false);//传给后面两个网关
                        return null;
                    }
                } else {
                    ctx.setSendZuulResponse(false); //过滤该请求不对其进行路由
                    ctx.setResponseBody("登陆失效，清重新登陆");
                    ctx.set("isSuccess",false);//传给后面两个网关
                    return null;
                }
             }
        }
}
