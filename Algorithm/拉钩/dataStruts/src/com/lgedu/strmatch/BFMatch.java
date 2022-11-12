package com.lgedu.strmatch;

/**
 * 暴力匹配法
 */
public class BFMatch {
    /**
     * 检测匹配
     * @param main  主串
     * @param pattern  模式串
     * @return
     */
    public static boolean isMatch(String main,String pattern){

        //循环主串  n
        for(int i=0;i<=(main.length()-pattern.length());i++){
            //有匹配  循环子串 m
            if(main.substring(i,i+pattern.length()).equals(pattern)){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(isMatch("hello","lo"));


    }
}
