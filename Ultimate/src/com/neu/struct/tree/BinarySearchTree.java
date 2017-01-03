package com.neu.struct.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题：二叉搜索树
 * 定义：二叉搜索树又称为二叉排序树、二叉查找树。一棵二叉搜索树是以一棵二叉树来组织的，
 *      这样一棵树可以使用一个链表数据结构来表示，
 *      其中每个节点就是一个对象。除了key和卫星数据外，每个节点还包含实行left、right、parent，
 *      它们分别指向节点的左孩子、右孩子和双亲。
 *
 * 性质：①如果某个孩子节点和父节点不存在，则相应属性的值为null。根节点是树中唯一父节点为null的节点。
 *      ②在二叉搜索树中。对任何节点x，其左子树中的关键字最大不超过x.key，其右子树的关键字最小不低于x.key。
 *      ③二叉搜索树上的基本操作所花费的时间与这棵树的高度成正比。对于有n个节点的一棵完全二叉树来说，这些操作的最坏运行时间为O(lgn)
 * 定理：①如果x是一棵有n个节点的子树的根，那么调用inorder(x)需要O(n)时间
 *      ②在一棵高度为h的二叉搜索树上，动态集合上的操作tree_search(),tree_minmum(),tree_maxmum()
 *        tree_successor(),tree_predecessor(),tree_insert(),tree_delete()可以在O(h)时间内完成。
 * Created by lihongyan on 2015/11/8.
 */

public class BinarySearchTree {

    private class BinarySearchNode{
        private int key;
        private BinarySearchNode left;
        private BinarySearchNode right;
        private BinarySearchNode parent;
        public BinarySearchNode(){
            this.key = 0;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
        public BinarySearchNode(int data){
            this.key = data;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
        public int getKey() {
            return key;
        }
        @Override
        public String toString() {
            String leftValue = (left==null)?"空":String.valueOf(left.key);
            String rightValue = (right==null)?"空":String.valueOf(right.key);
            String result = "{"+"双亲("+this.getKey()+"):[左孩子("+leftValue+"),右孩子("+rightValue+")]}";
            return result;
        }
    }

    private BinarySearchNode root = null;
    private List<BinarySearchNode> nodeList = new ArrayList<BinarySearchNode>();

    public BinarySearchNode getRoot() {
        return root;
    }
    public void setRoot(int key){
        BinarySearchNode localRoot = new BinarySearchNode(key);
        this.root = localRoot;
    }
    /*
    *查询二叉搜索树 :在一棵二叉搜索树中查找一个具有给定关键字的节点
    * */
    //输入一个指向根节点的指针和一个关键字k，如果这个节点存在，tree_search_recursion()返回该节点
    //算法分析：tree_search_recursion()的运行时间为O(h)，其中h为这棵树的高度
    public BinarySearchNode tree_search_recursion(BinarySearchNode localRoot,int key){
        if(localRoot==null||localRoot.getKey()==key){
            return localRoot;
        }
        if(key<localRoot.getKey()){
            return tree_search_recursion(localRoot.left,key);
        }else{
            return tree_search_recursion(localRoot.right,key);
        }
    }
    //我们也可以采用while循环来展开递归，用一种迭代方式重写这个过程。对于大多数计算，迭代版本的效率要高的多
    public BinarySearchNode tree_search_while(BinarySearchNode localRoot,int key){
        while(localRoot!=null&&localRoot.getKey()!=key){
            if(key<localRoot.getKey()){
                localRoot = localRoot.left;
            }else{
                localRoot = localRoot.right;
            }
        }
        return localRoot;
    }
    /*
    * 最大关键字元素和最小关键字元素
    * */
    //最小关键字元素：通过从树根开始，沿着left孩子指针直到遇到一个null，我们总能在一棵二叉搜索树中找到该元素元素
    public BinarySearchNode tree_minimum(BinarySearchNode localRoot){
        while(localRoot!=null){
            localRoot = localRoot.left;
        }
        return localRoot;
    }
    //最大关键字元素：通过从树根开始，沿着right孩子指针直到遇到一个null位置，我们总能在一棵二叉搜索树中找到该元素
    public BinarySearchNode tree_maximum(BinarySearchNode localRoot){
        while(localRoot!=null){
            localRoot = localRoot.right;
        }
        return localRoot;
    }
    /*
    * 前驱和后继(中序遍历)
    * 给定一棵二叉搜索树中的一个节点，有时候需要按中序遍历的次序查找它的后继，如果所有的关键字互不相同，
    * 则一个节点x的后继是大于x.key的最小关键字的节点。
    * 一棵二叉搜索树的结构允许我们通过没有任何关键字的比较来确定一个节点的后继。
    * 如果后继存在，tree_successor()将返回一棵二叉搜索树中的节点x的后继；
    * 如果x是这棵树中的最大关键字，则返回null
    * */
    //把tree_successor()的过程分为两种情况：
    // ①如果节点x的右子树非空，那么x的后继恰是x右子树中的最左节点。
    // ②如果x的右子树为空并有一个后继y，那么后继y就是x的最底层祖先，并且后继y的左孩子也是x的一个祖先。
    //   为了找到y，只需简单地从x开始沿树而上直到遇到这样一个节点：这个节点(y)的左孩子是它(x)的双亲
    //分析：在一棵高度为h的树上，tree_successor()的运行时间为O(h)，
    //     因为该过程或者遵从一条简单路径沿树向上或者遵从简单路径沿树向下
    public BinarySearchNode tree_successor(BinarySearchNode x){
        if(x.right!=null){
            return tree_successor(x.right);
        }
        BinarySearchNode y = x.parent;
        while(y!=null&&y.right==x){
            x = y;
            y = y.parent;
        }
        return  y;
    }
    //tree_predecessor()
    public BinarySearchNode tree_predecessor(BinarySearchNode x){

        return null;
    }
    /*
    * 插入
    * 将一个新值v插入到一棵二叉搜索树T中，该过程以节点z作为输入，其中z.key=v,z.left=null,z.right=null。
    * 这个过程要修改T和z的某些属性，来把z插入到树中的相应位置上
    * 过程：tree_insert()从树根开始，指针x记录了一条指向下的简单路径，并查找要替换的输入项z的null，
    *      该过程保持遍历指针y作为x的双亲。初始化后，while循环使得这两个指针沿树向下移动，向左或向右移动
    *      取决于z.key和x.key的比较，直到x变为null为止。这个null占据的位置就是输入项z要放置的地方。
    *      我们需要遍历指针y，这是因为找到null是要知道z属于哪个节点
    * */
    public void tree_insert(BinarySearchTree T,int key){
        BinarySearchNode x = getRoot();
        BinarySearchNode y = null;
        BinarySearchNode z = new BinarySearchNode(key);
        while(x!=null) {
            y = x;
            if (z.getKey() < x.getKey()) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if(y==null){
            T.root = z;
        }else if(z.getKey()<y.getKey()){
            y.left = z;
        }else{
            y.right = z;
        }
    }
    /*
    * 给定一组key,创建二叉搜索树
    * */
    public void tree_build(BinarySearchTree T,int[] keys){
        //如果没有指定二叉搜索树的根节点，那么就选取keys中的中间元素作为根节点
        BinarySearchNode temp = T.getRoot();
        if(temp==null){
            T.setRoot(keys[keys.length/2]);
        }
        for(int i=0;i<keys.length;i++){
            if(i!=keys.length/2) {
                T.tree_insert(T, keys[i]);
            }
        }
    }
    /*
    * 删除
    *  从一棵二叉搜索树T中删除一个节点z的整体策略分为三种基本情况：
     * ①如果z没有孩子节点，那么只是简单地将它删除，并修改z的父亲节点，用null作为孩子来替换z
     * ②如果z只有一个孩子，那么将这个孩子提升到树中z的位置上，并修改z的父亲节点，用z的孩子节点来替换z
     * ③如果z有两个孩子，那么找z的后继y(一定在z的右子树中)，并让y占据树中z的位置。
     *   z的原来右子树部分成为y的新的右子树，并且z的左子树成为y的新的左子树。
    * */
    //a.如果z没有左孩子，那么用其右孩子来替换z，这个右孩子可以是null，也可以不是。当z的右孩子是null时，
    //  此种情况归为z没有孩子节点的情形，当z的右孩子非null时，这种情况就是z仅有一个孩子节点的情形，
    //  该孩子是其右孩子。
    //b.如果z仅有一个孩子且其孩子为其左孩子，那么就用其左孩子来替换z。
    //c.z既有一个左孩子又有一个右孩子。我们要查找z的后继y，这个后继位于z的右子树中并且没有左孩子，
    //  并且后继y为z的右子树关键字最小的节点。
    //  情况c具体分两种情况：
    //  1.如果y是z的右孩子，那么用y替换z，并仅留下y的右孩子
    //  2.y位于z的右子树中但并不是z的右孩子，在这种情况下，先用y的右孩子替换y，然后再用y替换z。
    //    具体地：假设z的右孩子为r，双亲为q，左孩子为l
    //           ①先用y自己的右孩子x替换y
    //           ②并且置y为r的双亲
    //           ③再置y为q的孩子和l的双亲
    public void tree_delete(BinarySearchTree T,int key){
        BinarySearchNode z = tree_search_recursion(T.root,key);

        if(z.left==null){
            tree_transplant(T,z,z.right);
        }else if(z.right==null){
            tree_transplant(T,z,z.left);
        }else{
            //寻找z的后继y
            BinarySearchNode y = tree_minimum(z.right);
            if(y.parent!=z){
                tree_transplant(T,y,y.right);//用y的右孩子替换y，并成为y的双亲的一个孩子
                y.right = z.right;//然后将z的右孩子变为y的右孩子
                y.right.parent = y;
            }
            tree_transplant(T,z,y);
            y.left = z.left;
            y.left.parent = y;
        }

    }
    //为了在二叉搜索树内移动子树，定义一个子过程tree_transplant()，
    //它是用另一棵子树替换一棵子树并成为其双亲的孩子节点。
    //当tree_transplant()用一棵以v为根的子树来替换一棵以u为根的子树时，节点u的双亲就变为v的双亲，
    //并且最后v成为u的双亲的相应孩子
    private void tree_transplant(BinarySearchTree T,BinarySearchNode u,BinarySearchNode v){
        if(u.parent==null){
            T.root = v;
        }else if(u==u.parent.left){
            u.parent.left = v;
        }else{
            u.parent.right = v;
        }
        //之前的操作允许v为null。当v不为null时，执行以下操作：
        if(v!=null){
            v.parent = u.parent;
        }
    }
    /*
    * 遍历
    * */
    //线序遍历
    public void tree_pre_order(BinarySearchNode localRoot){
        if(localRoot!=null){
            System.out.print(localRoot.getKey() + " ");
            tree_pre_order(localRoot.left);
            tree_pre_order(localRoot.right);
        }
    }
    //中序遍历
    public void tree_in_oreder(BinarySearchNode localRoot){
        if(localRoot!=null){
            tree_in_oreder(localRoot.left);
            System.out.print(localRoot.getKey() + " ");
            tree_in_oreder(localRoot.right);
        }
    }
    //后序遍历
    public void tree_post_order(BinarySearchNode localRoot){
        if(localRoot!=null){
            tree_post_order(localRoot.left);
            tree_post_order(localRoot.right);
            System.out.print(localRoot.getKey() + " ");
        }
    }


    /*
    * 随机构建二叉搜索树
    *    定义由n个关键字组成的一棵 随机构建二叉搜索树 为按随机次序插入这些关键字到一棵初始的空树中而生成的树，
    *    这里输入关键字n!个排列中的每个都是等可能地出现
    * 定理：一棵有n个关键字的随机构建二叉搜索树的期望高度为O(lgn)
    * */
    public static void main(String[] args){
        BinarySearchTree bst = new BinarySearchTree();
        int[] keys = {1,2,3,4,5,6,7,8,9,10};
        bst.tree_build(bst,keys);
        bst.tree_pre_order(bst.getRoot());
        System.out.println();
        bst.tree_in_oreder(bst.getRoot());
        System.out.println();
        bst.tree_post_order(bst.getRoot());
    }
}
