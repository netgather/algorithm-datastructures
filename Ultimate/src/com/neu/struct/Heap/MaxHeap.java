package com.neu.struct.Heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题：如何构建最大堆
 * 特点：每个节点的值都大于等于其左右孩子值的完全二叉树
 * 堆：堆是一棵完全二叉树，且满足每个节点的值都大于等于其左右孩子值
 * 如果将序列{k(1),k(2),...,k(n)}对应为一维数组，且序列中元素的下标与数组中的下标一致，
 * 即数组中下标为0的位置不存放数据元素，此时该序列可以看做是一棵完全二叉树
 * Created by lihongyan on 2015/11/6.
 */
public class MaxHeap {

    private List<Integer> heap = null;
    public List<Integer> getHeap() {
        return heap;
    }
    public MaxHeap(){}
    public MaxHeap(List<Integer> heap){
        this.heap = heap;
    }
    public void adjust(int i,int n){
        int child = -1;
        for(;i<n/2;){
            child = i*2;
            if(child+1<n&&heap.get(child)<heap.get(child+1)){
                child += 1;
            }
            if(heap.get(i)<heap.get(child)){
                swap(i,child);
                i = child;
            }else{
                break;
            }
        }
    }
    public void buildHeap(){
        if(this.heap==null){
           throw new RuntimeException("未初始化，不能创建堆！");
        }
        for(int i=heap.size()/2;i>=0;i--){
            adjust(i, heap.size());
        }
    }
    public void swap(int indexA,int indexB){
        if(indexA<0||indexA>=this.heap.size()){
            return;
        }else if(indexB<0||indexB>=this.heap.size()){
            return;
        }else{
            int temp = this.heap.get(indexA);
            this.heap.set(indexA, heap.get(indexB));
            this.heap.set(indexB, temp);
        }
    }
    public void sort(){
        for(int i=heap.size()-1;i>0;i--){
            swap(0,i);
            adjust(0,i);
        }
    }
    //插入
    public void insert(int value){
        this.heap.add(value);
        up(this.heap.size()-1);
    }
    public void up(int index){
        if(index>=1){
            int parent = index/2;
            if(heap.get(parent)<heap.get(index)){
                swap(index,parent);
                up(parent);
            }
        }
    }
    //删除
    public void remove(int index){
        if(index<0||index>heap.size()-1){
            throw new RuntimeException("越界异常！");
        }
        swap(index, heap.size() - 1);
        down(index);
        heap.remove(heap.size() - 1);
    }
    public void down(int index){
        int length = heap.size();
        int child = -1;
        if(index*2+1>=length){//没有右孩子,所以不可能有左孩子
            return;
        }else if(index*2+1<length){//有左右孩子
            child = 2*index;
            if(heap.get(child)<heap.get(child+1)){
                child += 1;
            }
        }else{//只有左孩子
            child = index*2;
        }
        if(heap.get(index)<heap.get(child)){
            swap(index,child);
            down(child);
        }
    }



    public static void main(String[] args){
        List<Integer> heap = new ArrayList<Integer>();
        //heap.add(45);heap.add(36);heap.add(18);heap.add(53);heap.add(72);
        //heap.add(30);heap.add(48);heap.add(93);heap.add(15);heap.add(35);
        heap.add(3);heap.add(2);heap.add(1);heap.add(4);//heap.add(5);
        heap.add(9);heap.add(8);heap.add(7);heap.add(10);heap.add(6);
        MaxHeap maxHeap = new MaxHeap(heap);
        maxHeap.buildHeap();
        System.out.println(maxHeap.getHeap());
        maxHeap.insert(5);
        System.out.println(maxHeap.getHeap());
        maxHeap.sort();
        System.out.println(maxHeap.getHeap());
    }
}
