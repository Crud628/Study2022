package com.lgedu.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {5, 8, 6, 3, 9, 2, 1, 7};
        //循环次数
        for (int i = 0; i < nums.length - 1; i++) {
            boolean isSort = true; //默认是排好的
            //两两比较  已经移到右侧的就不用再比较了
            for (int j = 0; j < nums.length - 1 - i; j++) {
                int tmp = 0;
                if (nums[j] > nums[j + 1]) {
                    isSort=false;
                    //大的右移 交换
                    tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
            //排好则跳出循环
            if(isSort) break;

        }
        for (int n : nums) {
            System.out.println(n);
        }

    }
}

