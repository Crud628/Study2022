package com.lgedu.sort;

import java.util.Arrays;

public class HeapSort {
    public  static  void sort(int[] arr){
        //1、把无序数组构建成大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
        //2、调整堆结构 堆顶与末尾元素交换，产生新堆，再调整
        // 最后一个：arr[i]  最大的：arr[0]
        for(int i=arr.length-1;i>0;i--){
            //最后和最大交换
            int t=arr[i];
            arr[i]=arr[0];
            arr[0]=t;
            //调整大顶堆
            adjustHeap(arr,0,i);
        }
    }

    private static void  adjustHeap(int[] arr,int parent,int len){
        //临时变量保存父节点的值
        int tmp=arr[parent];
        //左孩子
        int child=2*parent+1;
        while(child<len){
            // 右孩子大于左孩子
            if (child + 1 < len && arr[child + 1] > arr[child]) {
                //右孩子
                child++;
            }
            // child 是左孩子或右孩子 ,不用交换
            if(tmp>=arr[child])  break;
            //把子值付给父值
            arr[parent]=arr[child];
            //把子位置付给父位置
            parent=child;

            //找下一个左子
            child=child*2+1;
        }
        //把父值给子
        arr[parent]=tmp;

    }

    public static void main(String[] args) {
        int[] arr={7,6,4,3,5,2,10,9,8};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
