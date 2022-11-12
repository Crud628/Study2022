package com.lgedu.alth.half;

/**
 * 用递归的方式实现二分查找
 */
public class HalfDemo2 {
    /**
     * 查找
     * @param nums 有序数组
     * @param num  数字的最大个数
     * @param n 要找的数
     * @return
     */
    public  static int binarySearchRa(int[] nums,int num,int n){
        return bsearchUnit(nums,0,num-1,n);
    }
    private static  int bsearchUnit(int[] nums,int low,int high,int n){
        //没找到
        if(low>high) return -1;
        int mid=(low+high)/2;
        //找到了
        if(nums[mid]==n){
            return mid;
        }
        //low -->right
        else if (nums[mid]<n){
            return bsearchUnit(nums,mid+1,high,n);
        }
        //high  --->left
        else{
            return bsearchUnit(nums,low,mid-1,n);
        }
    }

    public static void main(String[] args) {
        int[] nums={1,3,5,7,9,12,15,18,20};
        System.out.println(binarySearchRa(nums,9,18));
    }
}
