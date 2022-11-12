package com.lgedu.ds.hashmap;

/**
 * 单链表
 */
public class ListNode {
    Node head; //头结点

    /**
     * 添加单链表结点
     *
     * @param key
     * @param value
     */
    public void addNode(String key, String value) {
        //在外界设置好head了
        if (head == null) return;
        //   创建结点
        Node node = new Node(key, value, null);
        //  临时变量
        Node tmp = head;

        //循环单链表
        while (true) {
                       //key相同覆盖值 从head开始
            if(key.equals(tmp.key)){
                tmp.value=value;
            }

            if(tmp.next==null){
                break;
            }

            //指向下一个
            tmp=tmp.next;
        }
        //在循环外挂载最后一个结点
        tmp.next=node;

    }

    /**
     * 获得值
     *
     * @param key
     * @return
     */
    public String getVal(String key) {
        if (head == null) return null;
        //只有一个结点
        if (head.next == null) {
            return head.value;
        }
        //遍历单链表
        else {
            Node tmp = head;
            while (tmp != null) {
                //找到匹配的key
                if (key.equals(tmp.key)) {
                    return tmp.value;
                }
                //指向下一个
                tmp = tmp.next;
            }
            return null;
        }

    }
}
