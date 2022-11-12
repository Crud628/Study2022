package com.lgedu.alth.ra;

/**
 * 斐波那契数列
 */
public class Fiber {
    public  static int fun(int n){
        // 递归结束条件
        if(n<=1) return n;
        //等价关系式  实现功能
        return fun(n-1)+fun(n-2);
    }

    public static void main(String[] args) {
        System.out.println(fun(50));
    }
}
