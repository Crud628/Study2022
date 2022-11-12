package com.lgedu.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 用邻接矩阵实现图
 */
public class Graph1 {
    //存储顶点的链表
    List vertexList;
    //二维数组 存储边 值是权值
    int[][] edges;
    int numEdge; //边数

    public Graph1(int n){
        edges=new int[n][n];
        vertexList=new ArrayList();
        numEdge=0;
    }
    public int getVertexNums(){
        return vertexList.size();
    }
    public int getNumEdge(){
        return numEdge;
    }
    public Object getVertex(int i){
        return vertexList.get(i);
    }
    public int getWeight(int i,int j){
        return edges[i][j];
    }
    //插入结点
    public void insertVertex(Object v){
        vertexList.add(v);
    }

    /**
     * 插入边
     * @param i  顶点1
     * @param j  顶点2
     * @param w   权值
     */
    public void insertEdge(int i,int j,int w){
        edges[i][j]=w;
        numEdge++;
    }

    public static void main(String[] args) {
        Graph1 graph1=new Graph1(4);
        graph1.insertVertex("A"); // 0
        graph1.insertVertex("B"); //1
        graph1.insertVertex("C"); //2
        graph1.insertVertex("D"); // 3

        //A-B 2
        graph1.insertEdge(0,1,2);
        // B-C 4
        graph1.insertEdge(1,2,4);
        // C-D 2
        graph1.insertEdge(2,3,2);
        // A-D 4
        graph1.insertEdge(0,3,4);

        System.out.println(graph1.getNumEdge());
        System.out.println(graph1.getVertexNums());
        System.out.println(graph1.getWeight(2,3));

    }
}
