package com.lgedu.alth.greedy;

/**
 * 部分背包——贪心算法
 */
public class Bag1 {
    //最大承重
    double max=0;

    public void getMaxValue(Goods[] glist){
        Goods[] glist2=sort(glist); //价值排序
        //当前总重
        double sum_w=0;

        for(int i=0;i<glist2.length;i++){
            sum_w+=glist2[i].weight;
            if(sum_w<=max){
                System.out.println(glist2[i].name+"取"+glist2[i].weight+"kg");
            }
            else{
                System.out.println(glist2[i].name+"取"+(max-(sum_w-glist2[i].weight))+"kg");
                return ;
            }
        }

    }
    //按价值排序
    private Goods[] sort(Goods[] goods){
        return goods;
    }

    public static void main(String[] args) {
        Bag1 bag1=new Bag1();
        Goods g1=new Goods("A",10,60); //6
        Goods g2=new Goods("B",20,100); //5
        Goods g3=new Goods("C",30,120); //4
        Goods[] goods={g1,g2,g3}; // 价值倒序
        bag1.max=50;
        bag1.getMaxValue(goods);
    }

}
