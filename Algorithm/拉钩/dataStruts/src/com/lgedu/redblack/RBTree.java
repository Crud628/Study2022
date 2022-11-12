package com.lgedu.redblack;

/**
 * 红黑树
 */
public class RBTree {
    RBTreeNode root;//根节点

    /**
     * 遍历节点
     * @param node
     */
    public void list(RBTreeNode node){
        if(node==null) return;
        //递归终止条件
        if(node.getLeft()==null&&node.getRight()==null){
            System.out.println(node);
            return ;
        }
        System.out.println(node);
        list(node.getLeft());
        list(node.getRight());
    }

    /**
     * 插入节点
     * @param key
     */
    public void insert(int key){
        RBTreeNode node=new RBTreeNode(key);
        //插入根节点
        if(root==null) {
            node.setBlack(true);//根是黑的
            root=node;
            return ;
        }

        RBTreeNode parent=root;
        RBTreeNode son=null;

        //左孩子
        if(key<=parent.getKey()){
            son=parent.getLeft();
        }
        //右孩子
        else {
            son=parent.getRight();
        }
        //递归查找
        while(son!=null){
            parent=son;
            if(key<=parent.getKey()){
                son=parent.getLeft();
            }
            else{
                son=parent.getRight();
            }
        }
        //添加左孩子
        if(key<=parent.getKey()){
            parent.setLeft(node);
        }
        //添加右孩子
        else{
            parent.setRight(node);
        }
        node.setParent(parent);

        //自平衡
        balanceInsert(node);

    }

    /**
     * 插入自平衡
     * @param node
     */
    private void balanceInsert(RBTreeNode node){
        RBTreeNode father,gFather;
        //父节点是红的
        while((father=node.getParent())!=null && father.isBlack()==false){
            gFather=father.getParent();
            //父节点在祖父节点的左边
            if(gFather.getLeft()==father){
                RBTreeNode uncle=gFather.getRight();
                if(uncle!=null && uncle.isBlack()==false){
                    //颜色反转
                    setBlack(father);
                    setBlack(uncle);
                    setRed(gFather);
                    node=gFather;
                    continue;
                }
                if(node==father.getRight()){
                    //左旋
                    leftRotate(father);
                    //交换
                    RBTreeNode tmp=node;
                    node=father;
                    father=tmp;
                }
                setBlack(father);
                setRed(gFather);
                //右旋
                rightRotate(gFather);

            }
            //父节点在祖父节点右边
            else{
                RBTreeNode uncle=gFather.getLeft();
                if(uncle!=null && uncle.isBlack()==false){
                    //颜色反转
                    setBlack(father);
                    setBlack(uncle);
                    setRed(gFather);
                    node=gFather;
                    continue;
                }
                if(node==father.getLeft()){
                    //右旋
                    rightRotate(father);
                    //交换
                    RBTreeNode tmp=node;
                    node=father;
                    father=tmp;
                }
                setBlack(father);
                setRed(gFather);
                //左旋
                leftRotate(gFather);
            }
        }
        setBlack(root);

    }

    /**
     * 左旋
     * @param node
     */
    private void leftRotate(RBTreeNode node){
        RBTreeNode right=node.getRight();
        RBTreeNode parent=node.getParent();
        // root
        if(parent==null){
            root=right;
            right.setParent(null);
        }

        else{
            if(parent.getLeft()!=null&&parent.getLeft()==node){
                parent.setLeft(right);
            }
            else{
                parent.setRight(right);
            }
            right.setParent(parent);

        }
        node.setParent(right);
        node.setRight(right.getLeft());
        if(right.getLeft()!=null){
            right.getLeft().setParent(node);
        }
        right.setLeft(node);


    }

    /**
     * 右旋
      * @param node
     */
    private void rightRotate(RBTreeNode node){
        RBTreeNode left = node.getLeft();
        RBTreeNode parent = node.getParent();
        if (parent == null) {
            root = left;
            left.setParent(null);
        } else {
            if (parent.getLeft() != null && parent.getLeft() == node) {
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
            left.setParent(parent);
        }
        node.setParent(left);
        node.setLeft(left.getRight());
        if (left.getRight() != null) {
            left.getRight().setParent(node);
        }
        left.setRight(node);

    }

    /**
     * 设置黑色
     * @param node
     */
    private void setBlack(RBTreeNode node){
        node.setBlack(true);
    }
    private  void setRed(RBTreeNode node){
        node.setBlack(false);
    }

    public static void main(String[] args) {
        RBTree rb=new RBTree();
        rb.insert(10);//根节点
        rb.insert(5);
        rb.insert(9);
        rb.insert(3);
        rb.insert(6);
        rb.insert(7);
        rb.insert(19);
        rb.insert(32);
        rb.insert(24);
        rb.insert(17);

        rb.list(rb.root);
    }



}
