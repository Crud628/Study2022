package com.lgedu.ds.hashmap;

/**
 * 手动HashMap
 */
    public class MyHashMap {
    //数组初始化 2的n次方
    ListNode[] map=new ListNode[8];
    //ListNode的个数
    int size;

    /**
     * 设置值
     * @param key
     * @param value
     */
    public void put(String key,String value){
        //该扩容了
        if(size>=map.length*0.75){
             System.out.println("map需要扩容");
             return;
        }
        //计算索引 数组下标
        int index=Math.abs(key.hashCode())%map.length;
        //获得该下标处的ListNode
        ListNode ln=map[index];

        //该下标处无值
        if(ln==null){
            //创建单链表
            ListNode lnNew=new ListNode();
            //创建头结点
            Node head=new Node(key,value,null);
            //挂载头结点
            lnNew.head=head;
            //把单链放到数组里
            map[index]=lnNew;
            size++;
        }
        //该下标有值，hash碰撞
        else {
            //单链表挂结点
            ln.addNode(key,value);
        }

    }

    /**
     * 取值
     * @param key
     * @return
     */
    public String get(String key){
        int index=Math.abs(key.hashCode())%map.length;
        ListNode ln=map[index];
        if(ln==null) return  null;
        return ln.getVal(key);

    }

    public static void main(String[] args) {
        MyHashMap hashMap=new MyHashMap();
        hashMap.put("m3","cccccc");
        hashMap.put("c1","kkkkkk");
        hashMap.put("c1","mmmmmmm");
        System.out.println(hashMap.get("c1"));
    }
}
