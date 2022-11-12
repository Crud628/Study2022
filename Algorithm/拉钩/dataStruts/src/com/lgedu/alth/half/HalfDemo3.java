package com.lgedu.alth.half;

public class HalfDemo3 {
    public static int binarySearch(int[] nums){
        int low=0; //低位
        int high=nums.length-1; //高位
        int mid; //中间


        while(low<high){
            mid=(low+high)/2;
            //偶数位
            if(mid%2==0){
                // 跟后面的比相等
                if(nums[mid]==nums[mid+1]){
                    //前面的对，向后折半查
                    low=mid+1;
                }
                //跟前面的比相同
                else if (mid>0&&nums[mid]==nums[mid-1]){
                    //后面的都对，向前折半查找
                    high=mid-1;
                }
                else{
                    return  nums[mid];
                }
            }
            //奇数位
            else{
                // 跟后面的比相等
                if(nums[mid]==nums[mid+1]){
                    //后面的对，向前折半查
                    high=mid-1;
                }
                //跟前面的比相同
                else if (nums[mid]==nums[mid-1]){
                    //前面的都对，向后折半查找
                    low=mid+1;
                }
                else{
                    return  nums[mid];
                }

            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        int[] nums={1,1,2,2,3,3,6};
        System.out.println(binarySearch(nums));
    }
}
