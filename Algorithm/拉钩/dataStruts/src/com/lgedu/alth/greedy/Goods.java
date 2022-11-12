package com.lgedu.alth.greedy;

/**
 * 物品
 */
public class Goods {
    String name; //名称
    double weight; //重量
    double price; //价格
    double value; //价值

    public Goods(String name, double weight, double price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.value=price/weight;
    }
}
