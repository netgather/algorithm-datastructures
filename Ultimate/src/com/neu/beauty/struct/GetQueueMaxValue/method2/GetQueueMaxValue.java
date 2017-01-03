package com.neu.beauty.struct.GetQueueMaxValue.method2;

/**
 * 问题：获取队列中的最大值
 *      假设有这样一个拥有3个操作的队列
 *      1、push(v):将v加入队列中
 *      2、pop():使队列中的首元素删除并返回此元素
 *      3、maxElement():返回队列中的最大元素
 *     设计一种数据结构和算法，让maxElement()操作的时间复杂度尽可能地低。
 * 注解：队列是遵守“先进先出”原则的一种复杂数据结构。其底层的数据结构不一定要用数组来实现，
 *      还可以使用其他特殊的数据结构来实现，以达到降低maxElement()操作复杂度的目的。
 * Created by lihongyan on 2015/11/5.
 */
class HeapNode{
    public int data;
    public HeapNode left;
    public HeapNode right;
    public HeapNode next;
    public HeapNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
        this.next = null;
    }
}
public class GetQueueMaxValue {
    //方法二、根据取最大值的要求，可以考虑用最大堆来维护队列中的元素。
    //      堆中每个元素都有指针指向它的后继元素，
    //      这样，堆能够保证队列的正常插入和删除，
    //      同时也能很快完成返回最大元素的操作
    //      maxElement()操作其实就是维护一个最大堆，
    //      其时间复杂度为O(1)，入队和出队操作的时间复杂度为O(logn)
    private HeapNode root = null;
    public void insert(int data){
        HeapNode newNode = new HeapNode(data);
        if(root==null){
            root = newNode;
        }else{

        }
    }
    public void buildHeap(int node){
        insert(node);
    }
    public void buildHeap(int[] nodes){
        for(int i=0;i<nodes.length;i++){
            insert(nodes[i]);
        }
    }
    public void reBuildHeap(){

    }
    public int maxElement(){
        HeapNode temp = this.root;
        if(temp==null){
            return Integer.MIN_VALUE;
        }else{
            return temp.data;
        }
    }
    public static void main(String[] args){
        int[] array = {2,8,7,4,9,3,1,6,7,5};
        GetQueueMaxValue gqmv = new GetQueueMaxValue();
        gqmv.buildHeap(array);
        System.out.println(gqmv.maxElement());
    }
}
