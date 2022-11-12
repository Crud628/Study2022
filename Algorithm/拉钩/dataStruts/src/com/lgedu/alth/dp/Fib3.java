package com.lgedu.alth.dp;

/**
 * 斐波那契数列——DP方程（动态规划）
 */
public class Fib3 {
    public static long fib(int n){
        long a[]=new long[n+1];
        //初始值
        a[0]=0;
        a[1]=1;
        int i=0;
        for(i=2;i<=n;i++){
            //dp方程 dp(n)=dp(n-1)+dp(n-2)
            a[i]=a[i-1]+a[i-2];
        }
        return a[i-1];
    }

    public static void main(String[] args) {
        System.out.println(fib(64));
    }
}
