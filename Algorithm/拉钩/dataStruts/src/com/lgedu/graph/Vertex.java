package com.lgedu.graph;

/**
 * 顶点
 */
public class Vertex {
    String name;//出发点名称
    Edge next;//从该顶点出发的边

    public Vertex(String name, Edge next) {
        this.name = name;
        this.next = next;
    }
}
