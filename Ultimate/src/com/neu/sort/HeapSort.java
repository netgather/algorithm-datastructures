package com.neu.sort;

/**
 * 注释：①堆是一种特殊的树形数据结构，其每个节点都有一个值，通常提到的堆都是指一个完全二叉树，
 *         根节点的值小于（或大于）两个子节点的值，同时，根节点的两个子树也分别是一个堆。
 *       ②堆排序是一种树形选择排序，在排序的过程中，将array[1...n]看做一颗完全二叉树的顺序存储结构
 *         利用完全二叉树中父节点和子节点之间的内在关系来选择最小的元素
 *       ③堆一般分为：大顶堆和小顶堆两种不同的类型。大顶堆：堆顶元素为最大值；小顶堆：堆顶元素为最小值。
 * Created by lihongyan on 2015/10/31.
 */
public class HeapSort {
    //基本原理：对于给定的n个记录，初始时把这些记录看做一颗顺序存储的二叉树，然后将其调整为大顶堆。
    //          然后将堆的最后一个元素与堆顶元素进行交换。交换后，堆的最后一个元素即为最大元素；
    //          接着将前(n-1)个元素(即不包括最大记录)重新调整为一个大顶堆，再将堆顶元素与当前的最后一个元素进行交换，
    //          交换后得到次大的记录。重复该过程，直到调整的堆中只说给你下一个元素时为止，该元素即为最小记录。
    //步骤：①构建堆；②交换堆顶元素与最后一个元素的位置
    public static void adjust(int[] array,int position,int length){
        int temp = 0;
        int child = 0;
        for(temp=array[position];2*position+1<=length;position=child){
            child = 2*position + 1;
            if(child<length&&array[child]>array[child+1]){
                child++;
            }
            if(array[child]<temp){
                array[position] = array[child];
            }else{
                break;
            }
        }
        array[position] = temp;
    }
    public static void heapSort(int[] array){
        if(array==null){
            return;
        }
        int i;
        int length = array.length;
        for(i=length/2-1;i>=0;i--){
            adjust(array,i,length-1);
        }
        for(i=length-1;i>=0;i--){
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            adjust(array,0,i-1);
        }
    }
    public static void main(String[] args){
        int[] array = {5,4,9,8,7,6,0,1,3,2};
        heapSort(array);
        System.out.println("堆排序");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}
