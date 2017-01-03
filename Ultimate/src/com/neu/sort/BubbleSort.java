package com.neu.sort;

/**
 * 问题：如何进行冒泡排序
 * Created by lihongyan on 2015/10/31.
 */
public class BubbleSort {
    //基本原理：对于给定的n个记录，从第一个记录开始一次对相邻的两个记录进行比较，
    //        当前面的记录大于后面的记录时，交换位置，进行一轮比较和换位后，
    //        n个记录中的最大纪录位于第n位；然后对前(n-1)个记录进行第二轮比较；
    //        重复该过程，直到进行比较的记录只剩下一个为止
    public static void bubbleSort(int[] array){
        if(array==null){
            return;
        }
        int length = array.length;
        int temp = 0;
        for(int i=0;i<length;i++){
            for(int j=i+1;j<length;j++){
                if(array[i]>array[j]){
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    public static void main(String[] args){
        int[] array = {5,4,9,8,7,6,0,1,3,2};
        bubbleSort(array);
        System.out.println("冒泡排序");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}
