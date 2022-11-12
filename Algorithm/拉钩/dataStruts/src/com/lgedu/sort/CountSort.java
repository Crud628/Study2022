package com.lgedu.sort;

/**
 * 计数排序
 */
public class CountSort {
    public static int[] countSort(int[] array, int offset) {
        int[] nums = new int[array.length];

        for (int i = 0; i < array.length; i++) {

            int n = (array[i] - offset);
            //数字自增
            nums[n]++;
        }
        int[] nums2 = new int[array.length];
        // i是计数数组下标，k是新数组下标
        for (int i = 0, k = 0; i < nums.length; i++) {

            for (int j = 0; j < nums[i]; j++) {
                nums2[k++] = i + offset;
            }

        }
        return nums2;
    }

    public static void main(String[] args) {
        int[] scores = {95, 94, 91, 98, 99, 90, 99, 93, 91, 92};
        for (int n : countSort(scores, 90)) {
            System.out.println(n);
        }

    }
}
