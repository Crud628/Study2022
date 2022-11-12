package com.lgedu.list;

/**
 * 判断链表是否有环
 */
public class RingList {
    /**
     * 判断链表是否有环
     * @param head
     * @return
     */
    public static boolean isRing(Node head){
        if(head==null) return false;
        //定义快慢指针
        Node slow=head; //慢
        Node fast=head.next; //快

        while(fast!=null&&fast.next!=null){
            //追击相遇 有环
            if(slow==fast){
                return true;
             }
            fast=fast.next.next; //步长为2
            slow=slow.next;//步长为1

        }
        //无环
        return false;
    }

    public static void main(String[] args) {
        Node n1=new Node(1,"张飞");
        Node n2=new Node(2,"赵云");
        Node n3=new Node(3,"关羽");
        Node n4=new Node(4,"黄忠");
        Node n5=new Node(5,"狄仁杰");

        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next=null;

        System.out.println(isRing(n1));

    }
}
