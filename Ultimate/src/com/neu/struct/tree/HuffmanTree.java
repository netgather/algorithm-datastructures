package com.neu.struct.tree;

import java.util.*;

/**
 * 问题：如何实现赫夫曼树
 * 概念：赫夫曼树，又称为最优树，是一种带圈路径长度最短的树。
 *      路径：从树中一个节点到另一个节点之间的分支构成这两个节点之间的路径。
 *      路径长度：路径上的分支数目称作路径程度。
 *      树的路径长度：从树根到每一个节点的路径长度之和。
 *      节点带权路径长度：从该节点到树根之间的路径长度与节点上权的乘积。
 *      树的带全路径长度：树中所有叶子节点的带全路径长度之和。
 * 定义：假设有n个权值{w(1),w(2),...,w(n)}，试构造一棵有n个叶子节点的二叉树，每个叶子节点带全为w(i)，
 *      则其中带全路径长度WPL最小的为二叉树称作最优二叉树或赫夫曼树
 * 应用：在解某些问题时，利用赫夫曼树可以得到最佳判定算法。例如，要编制一个将百分制转换成五级分支的程序。
 * 构造：①根据给定的n个权值{w(1),w(2),...w(n)}构成n棵二叉树的集合F={T(1),T(2),...,T(n)}，其中每棵
 *        二叉树T(i)中只有一个带有权值为w(i)的根节点，其左右字数均为空。
 *      ②在F中选取两棵根的节点的权值最小的树作为左右子树构造一棵新的二叉树，且置新的二叉树的根节点的权值为
 *        其左、右子树上根节点的权值之和。
 *      ③在F中删除这两棵树，同时将新得到的二叉树加入F中。
 *      ④重复②和③，直到F只含一棵树为止，这棵树便是赫夫曼树。
 * Created by lihongyan on 2015/11/7.
 */
class HuffmanNode<T> implements Comparable<HuffmanNode<T>>{

    private T data;
    private double weight;
    private HuffmanNode<T> left;
    private HuffmanNode<T> right;
    public HuffmanNode(){}
    public HuffmanNode(T data,double weight){
        this.data = data;
        this.weight = weight;
    }

    public T getData() {
        return data;
    }

    public double getWeight() {
        return weight;
    }

    public HuffmanNode<T> getLeft() {
        return left;
    }

    public HuffmanNode<T> getRight() {
        return right;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setLeft(HuffmanNode<T> left) {
        this.left = left;
    }

    public void setRight(HuffmanNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "data:"+this.data+"  weight:"+this.weight;
    }

    @Override
    public int compareTo(HuffmanNode<T> other) {//从大到小排序
        if(this.weight>other.getWeight()){
            return -1;
        }else{
            return 1;
        }
    }
}
public class HuffmanTree<T> {

    public HuffmanNode<T> build(List<HuffmanNode<T>> nodes){
        while(nodes.size()>1){
            Collections.sort(nodes);//根据权值排序(重写compare()方法)，取权值集合中权值最小的两个根节点作为左右子节点。
            HuffmanNode<T> left = nodes.get(nodes.size() - 1);
            HuffmanNode<T> right = nodes.get(nodes.size() - 2);
            HuffmanNode<T> parent = new HuffmanNode<T>(null,left.getWeight()+right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            nodes.remove(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    public List<HuffmanNode<T>> breadth(HuffmanNode<T> root){
        List<HuffmanNode<T>> list = new ArrayList<HuffmanNode<T>>();
        Queue<HuffmanNode<T>> queue = new ArrayDeque<HuffmanNode<T>>();
        if(root!=null){
            queue.offer(root);
        }
        while(!queue.isEmpty()){
            list.add(queue.peek());
            HuffmanNode<T> node = queue.poll();
            if(node.getLeft()!=null){
                queue.offer(node.getLeft());
            }
            if(node.getRight()!=null){
                queue.offer(node.getRight());
            }
        }
        return list;
    }
    public static void main(String[] args){

        HuffmanTree<String> huffman = new HuffmanTree<String>();
        List<HuffmanNode<String>> list = new ArrayList<HuffmanNode<String>>();
        list.add(new HuffmanNode<String>("a",7));
        list.add(new HuffmanNode<String>("b",5));
        list.add(new HuffmanNode<String>("c", 2));
        list.add(new HuffmanNode<String>("d", 4));
        HuffmanNode<String> root = huffman.build(list);
        List<HuffmanNode<String>> nodes = huffman.breadth(root);
        System.out.print(nodes);
    }
}
