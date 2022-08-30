package com.example.dsandalgo;


import java.math.BigDecimal;
import java.util.ArrayList;

public class Test {
    @org.junit.jupiter.api.Test
    public void test(){
        ArrayList list = new ArrayList();
        list.add(new Object());


    }

    public static void main(String[] args) {
        BigDecimal percent =  new BigDecimal(0.99);

        for (int i = 100; i <10000; i++) {
            BigDecimal originPrice = new BigDecimal(i);
            BigDecimal multiply = originPrice.multiply(percent);
            BigDecimal promotionPrice = multiply.setScale(0, BigDecimal.ROUND_DOWN);
            long l = promotionPrice.divide(new BigDecimal(100), 1, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(100)).longValue();

            if (l >= originPrice.longValue()){
                System.out.println(originPrice);
            }

        }


    }
}
