package com.lgedu.homework;

import java.util.HashMap;
import java.util.Map;

/**
 * 查找唯一数
 */
public class UniqNum {

    public static boolean isUnique(int[] array){
        /*
            借助HashMap的key唯一性，也可以自己实现hash函数
         */
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<array.length;i++){
            if(map.containsKey(array[i])){
                return false;
            }
            //将array设置到key中，value任意
            map.put(array[i],1);
        }
        return true;
    }
    public static void main(String[] args) {
        int[] array={1,2,1};
        if(isUnique(array)){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }
}
