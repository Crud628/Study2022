package com.lgedu.alth.ra;

public class HelloWorld {
    public static void print(String ss) {
        for(int i=1;i<=5;i++){
            System.out.println(ss);
        }
    }


    public static void printRa(String ss,int n){
        //递归结束条件
        if(n<=0) return ;
        //函数功能
        System.out.println(ss);
        //调用自己，让参数趋近于递归结束条件
        printRa(ss,n-1);
    }

    public static void main(String[] args) {
        printRa("HelloWorld",5);
    }
}
