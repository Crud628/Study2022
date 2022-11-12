package com.lgedu.sort;

/**
 * 快速排序——双边循环法
 */
public class QuickSort1 {
    //递归排序
    public static  void  quick(int[] arr,int start,int end){
        //递归结束
           if(start>=end) {
               return ;
           }
       //获得基准元素位置
       int pivot=  pivot(arr,start,end);
           //前半段
        quick(arr,start,pivot-1);
            //后半段
        quick(arr,pivot+1,end);

    }

    /**
     * 返回基准位置
     * @return
     */
    private static int pivot(int[] arr,int start,int end){
        //取基准元素 第一个
        int pivot=arr[start];
        int left=start;
        int right=end;

        while(left!=right){
            //右指针左移
            while(left<right&&arr[right]>pivot){
                right--;
            }
            //左指针右移
            while(left<right&&arr[left]<=pivot){
                left++;
            }
            // 左右交换
            if(left<right){
                int t= arr[left];
                arr[left]=arr[right];
                arr[right]=t;
            }
        }
        //交换pivot和重合点
        arr[start]=arr[left];
        arr[left]=pivot;
        return left;
    }

    public static void main(String[] args) {
        int[] arr={4,7,3,5,6,2,8,1};
        quick(arr,0,arr.length-1);
        for(int i:arr){
            System.out.println(i);
        }
    }
}
