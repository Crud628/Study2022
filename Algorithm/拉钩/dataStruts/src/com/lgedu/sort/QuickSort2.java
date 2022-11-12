package com.lgedu.sort;

/**
 * 快速排序-单边循环法
 */
public class QuickSort2 {
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
        int mark=start;
        for(int i=start+1;i<=end;i++){
            if(arr[i]<pivot){
                mark++;
                int p=arr[mark];
                arr[mark]=arr[i];
                arr[i]=p;
            }
        }
        arr[start]=arr[mark];
        arr[mark]=pivot;
        return mark;

    }

    public static void main(String[] args) {
        int[] arr={4,7,3,5,6,2,8,1};
        quick(arr,0,arr.length-1);
        for(int i:arr){
            System.out.println(i);
        }
    }
}
