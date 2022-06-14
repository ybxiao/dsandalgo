package com.example.dsandalgo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class T_001 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("1", "2", "3");

        strings.stream().map(
                list ->{
                    return printT(strings);

                }


        ).collect(Collectors.toList());




    }

    private static List<String> printT(List<String> list) {
        return list.stream().map(s->{
            return s = s+"111";
        }).collect(Collectors.toList());
    }
}
