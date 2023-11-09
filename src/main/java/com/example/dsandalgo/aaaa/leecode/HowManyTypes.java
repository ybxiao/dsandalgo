package com.example.dsandalgo.aaaa.leecode;


import java.util.HashSet;
import java.util.Set;

/**
 * 只由小写字母（a~z）组成的一批字符串，都放在字符类型的数组String[] arr中，
 * 如果其中某两个字符串，所含有的字符种类完全一样，就将两个字符串算作一类 比如：baacba和bac就算作一类
 * 虽然长度不一样，但是所含字符的种类完全一样（a、b、c） 返回arr中有多少类？
 */
public class HowManyTypes {

    public static int types(String[] strings) {
        if (strings == null || strings.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();

        for (String str : strings) {
            boolean[] map = new boolean[26];
            char[] charArray = str.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                map[charArray[i] - 'a'] = true;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < map.length; i++) {
                if (map[i]) {
                    sb.append((char) ('a' + i));
                }
            }
            set.add(sb.toString());
        }
        return set.size();

    }


}
