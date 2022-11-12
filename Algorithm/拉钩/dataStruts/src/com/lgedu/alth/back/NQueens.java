package com.lgedu.alth.back;

/**
 * N皇后问题---回溯算法
 */
public class NQueens {
    static final int QUEENS = 8;//皇后数
    // 下标是行 值是列
    int[] result = new int[QUEENS]; // 存储放好的皇后

    static int sum=0;

    /**
     * 在指定行放皇后
     *
     * @param row
     */
    public void setQueens(int row) {
        //放置完成
        if (row == QUEENS) {
            print();
            return;
        }
        //循环列
        for (int col = 0; col < QUEENS; col++) {
            if (isOK(row, col)) {
                //存入数组
                result[row] = col;
                //开始下一行
                setQueens(row + 1);
            }
        }

    }

    /**
     * 判断是否可以放置
     *
     * @param row
     * @param col
     * @return
     */
    private boolean isOK(int row, int col) {
        int leftup = col - 1;//左对角线
        int rightup = col + 1;//右对角线
        for (int i = row - 1; i >= 0; i--) {
            //等于列 原列存在
            if (result[i] == col) {
                return false;
            }
            //左对角线
            if (leftup >= 0) {
                if (result[i] == leftup) return false;
            }
            if (rightup < QUEENS) {
                if (result[i] == rightup) return false;
            }
            leftup--;
            rightup++;
            sum++;
        }


        return true;
    }

    /**
     * 打印结果
     */
    private void print() {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                if (result[i] == j) {
                    System.out.print("Q|");
                } else {
                    System.out.print("*| ");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------");
    }

    public static void main(String[] args) {
        NQueens queens=new NQueens();
        queens.setQueens(0);
        System.out.println(sum);
        System.out.println(8*7*6*5*4*3*2*1);
    }
}
