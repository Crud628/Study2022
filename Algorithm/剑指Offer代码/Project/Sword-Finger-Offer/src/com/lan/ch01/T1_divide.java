package com.lan.ch01;

/**
 * 面试题1：整数除法
 * 
 * 输入两个int型整数，求它们除法的商，要求不得使用乘号'*'、除号'/'以及求余符号'%'。
 * 当发生溢出时返回最大的整数值。
 * 假设除数不为0。例如，输入15和2，输出15/2的结果，即7。
 * @author Keason
 *
 */
public class T1_divide {
	public int divide(int dividend, int divisor) {
	    if (dividend == 0x80000000 && divisor == -1){
	        return Integer.MAX_VALUE;
	    }

	    int negative = 2;
	    if (dividend > 0) {
	        negative--;
	        dividend = -dividend;
	    }

	    if (divisor > 0) {
	        negative--;
	        divisor = -divisor;
	    }

	    int result = divideCore(dividend, divisor);
	    return negative == 1 ? -result : result;
	}

	private int divideCore(int dividend, int divisor) {
	    int result = 0;
	    while (dividend <= divisor) {
	        int value = divisor;
	        int quotient = 1;
	        while (value >= 0xc0000000 && dividend <= value + value) {
	            quotient += quotient;
	            value += value;
	        }

	        result += quotient;
	        dividend -= value;
	    }

	    return result;
	}
}
