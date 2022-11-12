package com.lgedu.alth.dp;

/**
 * 斐波那契数列：递归分治+备忘录(动态规划)
 */
public class Fib2 {
    //用于存储每次计算的结果
    static long[] sub=new long[1000000];

    public static long fib(int n){
        if(n<=1) return n;
        //该数字已被计算
        if(sub[n]!=0){
            return sub[n];
        }
        else{
            sub[n]=fib(n-1)+fib(n-2);
        }
        return sub[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(64));
    }

}
