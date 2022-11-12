package com.lgedu.binarytree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉查找树
 */
public class BinarySerachTree {
    //根节点
    TreeNode root;

    //插入数据
    public void insertNode(int data){
        root=insert(root,data);
    }

    //递归插入节点
    private TreeNode insert(TreeNode node, int data){
        //结束条件
        if(node==null) return new TreeNode(data);

        //左孩子
        if(data<node.data){
            node.leftChild=insert(node.leftChild,data);
        }
        //右孩子
        else if(data>node.data){
            node.rightChild=insert(node.rightChild,data);
        }
        //自己
        else{
            node.data=data;
        }
        return node;
    }

    /**
     * 前序遍历--递归  父 左 右
     */
    public void beforeTraver(TreeNode node){
        //结束条件
        if(node==null) return ;


        System.out.println(node.data); //节点
        beforeTraver(node.leftChild); //左孩子
        beforeTraver(node.rightChild); //右孩子

    }

    /**
     * 中序遍历    左 父 右
     * @param node
     */
    public void midTraver(TreeNode node){
        //结束条件
        if(node==null) return ;
        midTraver(node.leftChild);
        System.out.println(node.data);
        midTraver(node.rightChild);

    }

    /**
     * 后序遍历  左 父  右
     * @param node
     */
    public void afterTraver(TreeNode node){
        //结束条件
        if(node==null) return ;
        afterTraver(node.leftChild);
        afterTraver(node.rightChild);
        System.out.println(node.data);

    }

    /**
     * 广度遍历
     * @param root
     */
    public  void levelTraver(TreeNode root){
        //队列
        Queue<TreeNode> queue=new LinkedList<>();
        //从队尾入队
        queue.offer(root);
        //队列不为空
        while(!queue.isEmpty()){
            //出队   队头出并删除
            TreeNode node=queue.poll();
            System.out.println(node.data);
            //左孩子入队
            if(node.leftChild!=null){
                queue.offer(node.leftChild);
            }
            //右孩子入队
            if(node.rightChild!=null){
                queue.offer(node.rightChild);
            }
        }

    }







    public static void main(String[] args) {
        BinarySerachTree bst=new BinarySerachTree();
        bst.insertNode(10);
        bst.insertNode(8);
        bst.insertNode(11);
        bst.insertNode(7);
        bst.insertNode(9);
        bst.insertNode(12);

        bst.beforeTraver(bst.root);
        System.out.println("===========================");
        bst.midTraver(bst.root);
        System.out.println("=============================");
        bst.afterTraver(bst.root);
        System.out.println("==============================");
        bst.levelTraver(bst.root);

    }


}
