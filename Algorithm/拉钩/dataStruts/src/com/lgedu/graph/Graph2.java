package com.lgedu.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 邻接表实现图
 */
public class Graph2 {
    Map<String, Vertex> vertexMap = new HashMap<>(); //存储顶点

    /**
     * 插入顶点
     *
     * @param name
     */
    public void insertVertex(String name) {
        Vertex vertex = new Vertex(name, null);
        vertexMap.put(name, vertex);
    }

    /**
     * 插入边
     *
     * @param start
     * @param end
     * @param weight
     */
    public void insertEdge(String start, String end, int weight) {
        Vertex vstart = vertexMap.get(start);
        if (vstart == null) {
            return; //没有顶点 不许插入边
        }
        Edge edge = new Edge(end, weight, null);
        //无边
        if (vstart.next == null) {
            vstart.next = edge;
        }
        //有边 循环找到最后一条边
        else {
            Edge last = vstart.next;
            while (last.next != null) {
                last = last.next;
            }
            last.next = edge;
        }

    }

    /**
     * 打印图
     */
    public void print() {
        Set<Map.Entry<String, Vertex>> set = vertexMap.entrySet();
        Iterator<Map.Entry<String, Vertex>> its = set.iterator();
        while (its.hasNext()) {
            Map.Entry<String, Vertex> entry = its.next();
            Vertex vertex = entry.getValue();
            Edge edge = vertex.next;
            while (edge != null) {
                System.out.println(vertex.name + "指向" + edge.name + "权值为：" + edge.weight);
                edge = edge.next;
            }
        }
    }

    public static void main(String[] args) {
        Graph2 graph2=new Graph2();
        //插入顶点
        graph2.insertVertex("A");
        graph2.insertVertex("B");
        graph2.insertVertex("C");
        graph2.insertVertex("D");
        graph2.insertVertex("E");
        graph2.insertVertex("F");
        //插入边
        graph2.insertEdge("A","B",4);
        graph2.insertEdge("A","D",5);
        graph2.insertEdge("C","A",1);
        graph2.insertEdge("D","E",3);
        graph2.insertEdge("D","F",4);
        graph2.insertEdge("E","B",2);
        graph2.insertEdge("F","C",2);

        graph2.print();

    }
}

