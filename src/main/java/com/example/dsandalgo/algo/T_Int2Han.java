package com.example.dsandalgo.algo;


import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class T_Int2Han {


    public static void main(String[] args) {
        System.out.println(convert(412034));
        List<String> list = new ArrayList<>();

    }


    public static String convert(int i){
        String[] danwei = new String[]{"","十","百","千","万","十万"};
        List<String> result = new ArrayList<>();
        char a = '中';
        System.out.println(a);
        int div = 10;
        int k = 0;
        while (i > 0){
            StringBuilder sb = new StringBuilder();
            sb.append(getConvers(i%10));
            if (i%10 > 0 && !StringUtils.isEmpty(danwei[k])){
                sb.append(danwei[k]);
            }
            result.add(sb.toString());
            i=i/10;
            k++;


        }
        StringBuilder sb = new StringBuilder();

        for (int j = result.size()-1; j >=0; j--) {

            sb.append(result.get(j));
        }

        return sb.toString();
    }

    public static String getConvers(int i){
        switch (i){
            case 0:
                return "零";
            case 1:
                return "壹";
            case 2:
                return "贰";
            case 3:
                return "叁";
            case 4:
                return "肆";
            default:
                return "";



        }



    }
}
