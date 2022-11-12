package com.lgedu.strmatch.trie;

import com.lgedu.binarytree.TreeNode;

/**
 * Trie树节点
 */
public class TrieNode {
    char data;//数据
    TrieNode[] children=new TrieNode[26]; //孩子节点
    boolean isLeaf=false; //是否叶子

    public TrieNode(char data) {
        this.data = data;
    }
}
