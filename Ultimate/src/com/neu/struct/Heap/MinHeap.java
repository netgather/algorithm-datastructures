package com.neu.struct.Heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题：如何创建最小堆
 * Created by lihongyan on 2015/11/7.
 */
public class MinHeap {

    private List<Integer> heap = null;
    public List<Integer> getHeap() {return heap;}
    public MinHeap(){}
    public MinHeap(List<Integer> heap){this.heap = heap;}


    public void swap(int indexA,int indexB){
        if(indexA<0||indexA>heap.size()-1){
            return;
        }else if(indexB<0||indexB>heap.size()-1){
            return;
        }else{
            int temp = heap.get(indexA);
            heap.set(indexA, heap.get(indexB));
            heap.set(indexB, temp);
        }
    }
    public void adjust(int i,int n){
        int child = -1;
        for(;i<n/2;){
            child = i*2;
            if(child+1<n&&heap.get(child)>heap.get(child + 1)){//选取两个孩子中较小的一个
                child += 1;
            }
            if(heap.get(child)<heap.get(i)){
                swap(child,i);
                i = child;
            }else{
                break;
            }
        }
    }
    public void buildHeap(){
        int length = heap.size();
        for(int i=length/2;i>=0;i--){
            adjust(i,length);
        }
    }



    //插入
    public void insert(int value){
        heap.add(value);
        up(heap.size()-1);
    }
    public void up(int index){
        if(index>=1){
            int parent = index/2;
            if(heap.get(parent)>heap.get(index)){
                swap(parent,index);
                up(parent);
            }
        }
    }
    //删除
    public void remove(int index){
        if(index<0||index>=heap.size()){
            return;
        }else{
            swap(index,heap.size()-1);
            down(index);
            heap.remove(heap.size() - 1);
        }
    }
    public void down(int index){
        int child = -1;
        if(index*2>=heap.size()){//无左右孩子
            return;
        }else if(index*2<heap.size()-1){//有左右孩子
            child = index*2;
            if(heap.get(child)>heap.get(child+1)){
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
    //排序
    public void sort(){
        for(int i=heap.size()-1;i>0;i--){
            swap(0,i);
            adjust(0,i);
        }
    }
    public static void main(String[] args){
        List<Integer> heap = new ArrayList<Integer>();
        heap.add(3);heap.add(2);heap.add(1);heap.add(4);heap.add(5);
        heap.add(9);heap.add(8);heap.add(7);heap.add(10);heap.add(6);
        MinHeap minHeap = new MinHeap(heap);
        minHeap.buildHeap();
        System.out.println(minHeap.getHeap());
        minHeap.remove(5);
        System.out.println(minHeap.getHeap());
        minHeap.sort();
        System.out.println(minHeap.getHeap());
    }
}
