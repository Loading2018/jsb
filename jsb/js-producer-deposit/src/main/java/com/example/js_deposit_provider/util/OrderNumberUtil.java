package com.example.js_deposit_provider.util;

import java.util.Random;

public class OrderNumberUtil {
    public static String createNumber(String userId){
        long l = System.currentTimeMillis();
        String result = "";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        String number = l + result +userId;
        return  number;
    }
}
