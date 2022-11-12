package com.lgedu.homework;

/**
 * 0-1背包，计算最大价值  DP方程
 */
public class Gold {
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
        int mv=dp[values.length][max];
        //逆推找出装入背包的所有商品的编号（选的矿的编号）
        int j=max;
        String numStr="";
        for(int i=values.length;i>0;i--){
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的（第i个矿挖）
            if(dp[i][j]>dp[i-1][j]){
                numStr = i+" "+numStr;
                j=j-weights[i-1];
            }
            if(j==0)
                break;
        }
        System.out.println("选择的金矿有："+numStr);

        return mv;

    }

    public static void main(String[] args) {
        int[] values={60,30,50,40,60};
        int[] weights={2,2,6,5,4};
        int max=10;
        System.out.println("挖出的最大储量是："+maxValue(values,weights,max));
    }
}
