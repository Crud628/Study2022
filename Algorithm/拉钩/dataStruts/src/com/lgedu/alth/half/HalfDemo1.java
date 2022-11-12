package com.lgedu.alth.half;

/**
 * 在有序数组中找到某个数的位置
 */
public class HalfDemo1 {
     public static int binarySerachComm(int[] nums,int n){
         //低位索引
         int low=0;
         //高位索引
         int high=nums.length-1;
         //中间索引
         int mid=0;

         //可查找
         while(low<=high){
            mid=(low+high)/2;
            if(n==nums[mid]){
                return mid;
            }
            else if(n>nums[mid]){
                low=mid+1;
            }
            // n<nums[mid]
            else{
                high=mid-1;

            }
         }
         return -1;

     }

    public static void main(String[] args) {
        int[] nums={1,3,5,7,9,12,15,18,20};
        System.out.println(binarySerachComm(nums,19));
    }

}
