package com.lgedu.graph;

/**
 * 边
 */
public class Edge {
    String name; // 到达的顶点名称
    int weight; //权重
    Edge next;//指向的下一个边

    public Edge(String name, int weight, Edge next) {
        this.name = name;
        this.weight = weight;
        this.next = next;
    }

}
