package com.lgedu.strmatch.trie;



/**
 * Trie树
 */
public class Trie {
    TrieNode root=new TrieNode('/');//根节点

    /**
     * 插入到Trie树
     * @param txt
     */
    public void insert(char[] txt){
        TrieNode p=root;
        for(int i=0;i<txt.length;i++){
            //索引位
            int idx=txt[i]-97;
            if(p.children[idx]==null){
                TrieNode node=new TrieNode(txt[i]);
                p.children[idx]=node;
            }
            //指向下一个孩子
            p=p.children[idx];
        }
        //最后一个字母
        p.isLeaf=true;

    }

    /**
     * 在Trie树中查找一个字符串
     * @param pattern
     * @return
     */
    public boolean find(char[] pattern){
        TrieNode p=root;
        for(int i=0;i<pattern.length;i++){
            int idx=pattern[i]-97;
            //只要一个字母不匹配没有匹配
            if(p.children[idx]==null) {
                return false;
            }
            p=p.children[idx];
        }
        //没有找到最后一个字母
        if(p.isLeaf==false){
            //只能匹配前缀
           return false;
        }
        else{
            //完全匹配
            return true;
        }
    }

    public static void main(String[] args) {
        Trie trie=new Trie();
        trie.insert("hello".toCharArray());
        trie.insert("her".toCharArray());
        trie.insert("hi".toCharArray());
        trie.insert("how".toCharArray());
        trie.insert("see".toCharArray());
        trie.insert("so".toCharArray());

        System.out.println(trie.find("ho".toCharArray()));
    }
}
