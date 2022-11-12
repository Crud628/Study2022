package com.lgedu.alth.divide;

/**
 * 计算n次幂——分治
 */
public class Pow {
    /**
     * 计算x的n次幂
     * @param x
     * @param n
     * @return
     */
    public static int pow(int x, int n){
        //递归结束 任何数的1次方都是它本身
        if(n==1) return x;
        int half=pow(x,n/2);
        //偶数
        if(n%2==0){
            return half*half;
        }
        //奇数
        else{
            return half*half*x;
        }

    }

    public static void main(String[] args) {
        System.out.println(pow(2,10));
    }
}
