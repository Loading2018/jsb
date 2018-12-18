package com.example.jsspringzuul.uitl;

import com.example.jsspringzuul.key.CommonConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class CreatUtil {
    public static String generateJwt(Claims claims) {//参数传一个用户对象放到map集合中
        // Base64编码后的secretKey
        byte[] secretKey = Base64.getEncoder().encode(CommonConstants.SECURITY_KEY.getBytes());
        // 设置失效时间
        Date expirationDate = new Date();
        Calendar calendar = Calendar.getInstance();
        //设置一天过期
        calendar.add(Calendar.DATE,1);
        Date time = calendar.getTime();
        //DateTime expirationDate = new DateTime().plusMinutes(30);
        // Claims是需要保存到token中的信息，可以自定义，需要存什么就放什么，会保存到token的payload中
//        Map<String, Object> claims = new HashMap<>();
//
//        // 放用户对象
//        claims.put("","");
        String compactJws = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS512")
                // 设置失效时间
                .setExpiration(time)
                //主体
                .setClaims(claims)
                //也可以用.addClaims()添加数据
                // 加密算法是HS512，加密解密统一就可以
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        //放redis 设置5分钟
        return compactJws;
    }
}
