package com.neu.sort;

/**
 * 问题：如何进行插入排序
 * Created by lihongyan on 2015/10/30.
 */
public class InsertSort {
    //基本原理：对于给定的一组记录，假设第一个记录自成一个有序序列，其余记录为无序序列。
    //          从第二个记录开始，按照记录的大小依次将当前处理的记录插入到其之前的有序序列中，
    //          直到最后一个记录插入到有序序列中为止。
    public static void insertSort(int[] array){
        if(array==null){
            return;
        }
        int length = array.length;
        int temp = 0;
        int j = 0;
        for(int i=1;i<length;i++){
            /*int */temp = array[i];
            /*int*/ j = i;
            if(array[j-1]>temp){
                while(j>=1&&array[j-1]>temp){
                    array[j] = array[j-1];
                    j--;
                }
                array[j] = temp;
            }
        }
    }
    public static void main(String[] args){
        int[] array = {5,4,9,8,7,6,0,1,3,2};
        insertSort(array);
        System.out.println("插入排序");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}
