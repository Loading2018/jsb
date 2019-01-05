package com.example.js_deposit_provider.util;

import java.math.BigDecimal;

public class BusinessRateUtil {

    public static Double interest(Double principal,Integer depositBusinessTime,Double depositBusinessRate){
        return new BigDecimal(principal*depositBusinessRate/100/360*depositBusinessTime)
                .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Double principalAndInterest(Double principal,Integer depositBusinessTime,Double depositBusinessRate){
        return new BigDecimal(principal * depositBusinessRate / 100 / 360 * depositBusinessTime)
                .add(new BigDecimal(principal))
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    public static Double addTogether(Double principal,Double interest){
        return new BigDecimal(principal)
                .add(new BigDecimal(interest))
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

    }
}
