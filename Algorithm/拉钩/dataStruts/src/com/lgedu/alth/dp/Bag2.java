package com.lgedu.alth.dp;

/**
 * 0-1背包，计算最大价值  DP方程
 */
public class Bag2 {
    /**
     * 计算最大价值
     * @param values 物品的价值数组
     * @param weights 物品的重量数组
     * @param max 背包最大承重
     * @return
     */
    public static  int maxValue(int[] values,int[] weights,int max){
        if(values==null|| values.length==0) return 0;
        if(weights==null|| weights.length==0) return 0;
        if(values.length!=weights.length|| max<=0) return 0;

        //dp数组  dp[i-1]  i从1开始
        int[][] dp=new int[values.length+1][max+1];

        for(int i=1;i<=values.length;i++){
            for(int j=1;j<=max;j++){
                //选择的物品超过最大承重
                if(weights[i-1]>j){
                    //不能选该物品 等于上轮的最大价值
                    dp[i][j]=dp[i-1][j];
                }
                //选择的物品不超过最大承重
                else{
                    //上轮的最大价值
                    int proValue=dp[i-1][j];
                    //选择该商品后的最大价值
                    int curValue=values[i-1]+dp[i-1][j-weights[i-1]];
                    //两者取最大值
                    dp[i][j]=Math.max(proValue,curValue);
                }

            }
        }
        return dp[values.length][max];

    }

    public static void main(String[] args) {
        int[] values={6,3,5,4,600};
        int[] weights={2,2,6,5,10};
        int max=10;
        System.out.println(maxValue(values,weights,max));
    }
}
