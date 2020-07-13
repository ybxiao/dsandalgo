package com.example.dsandalgo.algo;

public class T_isPalindrome {

    public boolean isPalindrome(int i ){

        String s = new StringBuilder(i + "").reverse().toString();
        return s.equals(String.valueOf(i));

    }

    public boolean isPalindrome1(int x ){
        while (x < 0){

            return false;
        }
        int div =1;
        //10034
        while (x /div >=10) div *= 10;

        while (x >0) {
          int i  = x /div;
          int j = x%10;
          if (i !=j) return false;
          x= (x%div)/10;
          div = div/100;

        }






        return true;
    }
}
