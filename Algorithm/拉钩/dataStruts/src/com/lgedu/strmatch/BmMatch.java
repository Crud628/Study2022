package com.lgedu.strmatch;

/**
 * 滑动匹配法
 */
public class BmMatch {
    /**
     * 模式串字典
     * @param b
     * @param m
     * @param dc
     */
    private static void genBC(char[] b,int m,int[] dc){
        //初始化字典
        for(int i=0;i<256;i++){
            dc[i]=-1;
        }
        //将模式串中的字符写入字典中
        for(int i=0;i<m;i++){
            int asc=(int)b[i];
            //asc 是下标  i 是模式串中的位置
            dc[asc]=i;
        }
    }

    /**
     * BM算法匹配
     * @param main
     * @param pattern
     * @return
     */
    public static int bad(char[] main,char[] pattern){
        int n=main.length;
        int m=pattern.length;

        int[] bc=new int[256];

        genBC(pattern,m,bc);
        //对齐的第一个字符
        int i=0;
        while(i<=n-m){
            //坏字符在模式串中的下标
            int j;
            for(j=m-1;j>=0;j--){
                if(main[i+j]!=pattern[j]) break;
            }
            //匹配成功
            if(j<0){
                //返回位置
                return i;
            }
            //滑动位  si-xi
            i=i+(j-bc[(int)main[i+j]]);
        }

        return -1;

    }

    public static void main(String[] args) {
        String s1="abcabcabcd";
        String s2="cabcd";
        System.out.println(bad(s1.toCharArray(),s2.toCharArray()));
    }
}
