package com.lgedu.ds.hashmap;

/**
 * 结点
 */
public class Node {
    String key;
    String value;
    //指向下一个结点
    Node next;

    public Node(String key, String value, Node next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
