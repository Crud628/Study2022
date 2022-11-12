package com.lgedu.strmatch;

/**
 * hash匹配法
 */
public class RKMatch {

    /**
     * Hash匹配
     * @param main 主串
     * @param pattern  模式串
     * @return
     */
    public static boolean isMatch(String main,String pattern){
        //计算模式串的hash值
        int ph=strTohash(pattern);
        for(int i=0;i<=(main.length()-pattern.length());i++){
            if(ph==strTohash(main.substring(i,i+pattern.length()))){
                return true;
            }
        }
        return false;

    }

    /**
     * 简单hash计算法
     * @param src
     * @return
     */
    public static int strTohash(String src){
        int hash=0;
        for(int i=0;i<src.length();i++){
            //26进制  小写字母 a-z
            hash*=26;
            hash+=src.charAt(i)-97;
        }
        return hash;
    }

    public static void main(String[] args) {
        //System.out.println(strTohash("abc"));
        System.out.println(isMatch("abcddff","abe"));
    }

}
