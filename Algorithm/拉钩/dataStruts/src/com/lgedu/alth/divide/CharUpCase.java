package com.lgedu.alth.divide;

/**
 * 字符串小写字母转大写字母---分治算法
 */
public class CharUpCase {

    public static char[] toUpCase(char[] arr,int i){
        //递归结束条件
        if(i>=arr.length){
            return arr;
        }
        arr[i]=toUpCaseUnit(arr[i]);
        return toUpCase(arr,i+1);
    }

    /**
     * 单元方法 小写转大写
     * @param a
     * @return
     */
    private static char toUpCaseUnit(char a){
        //不是字母
        if((int)a<97||(int)a>122){
            return ' ';
        }
        return  (char)Integer.parseInt(String.valueOf((int)a-32));
    }

    public static void main(String[] args) {
        char[] arr=toUpCase("abcdde".toCharArray(),0);
        System.out.println(arr);
    }
}
