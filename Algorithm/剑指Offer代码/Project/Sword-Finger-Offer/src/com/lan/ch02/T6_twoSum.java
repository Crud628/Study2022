package com.lan.ch02;

/**
 * 面试题6：排序数组中两个数字之和
 * 
 * 输入一个递增排序的数组和一个值k，请问如何在数组中找出两个和为k的数字并返回它们的下标？
 * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
 * 例如输入数组[1, 2, 4, 6, 10]，k的值为8，
 * 数组中的数字2和6的和为8，它们的下标分别为1和3。
 * @author Keason
 *
 */
public class T6_twoSum {
	public int[] twoSum(int[] numbers, int target) {
	    int i = 0;
	    int j = numbers.length - 1;
	    while (i < j && numbers[i] + numbers[j] != target) {
	        if (numbers[i] + numbers[j] < target) {
	            i++;
	        } else {
	            j--;
	        }
	    }

	    return new int[] {i, j};
	}
}
