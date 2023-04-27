package com.lan.ch01;

/**
 * 面试题3：前n个数字二进制中1的个数
 * 
 * 输入一个非负数n，请计算0到n之间每个数字的二进制表示中1的个数，并输出一个数组。
 * 例如，输入n为4，由于0、1、2、3、4的二进制表示的1的个数分别为0、1、1、2、1，
 * 因此输出数组[0, 1, 1, 2, 1]。
 * @author Keason
 *
 */
public class T3_countBits {
	public int[] countBits(int num) {
	    int[] result = new int[num + 1];
	    for (int i = 0; i <= num; ++i) {
	        int count = 0;
	        int j = i;
	        while (j != 0) {
	            result[i]++;
	            j = j & (j - 1);
	        }
	    }

	    return result;
	}
	
	public int[] countBits2(int num) {
	    int[] result = new int[num + 1];
	    for (int i = 1; i <= num; ++i) {
	        result[i] = result[i & (i - 1)] + 1;
	    }

	    return result;
	}
	
	public int[] countBits3(int num) {
	    int[] result = new int[num + 1];
	    for (int i = 1; i <= num; ++i) {
	        result[i] = result[i >> 1] + (i & 1);
	    }

	    return result;
	}
}
