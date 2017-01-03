package com.neu.sort;

/**
 * 问题：如何进行选择排序
 * Created by lihongyan on 2015/10/30.
 */
public class SelectSort {
    //基本原理：对于给定的一组记录，经过第一轮比较后得到 最小 的记录，
    //          然后将该记录与第一个记录的位置进行交换；
    //          接着对不包括第一个记录以外的其他记录进行第二轮比较，
    //          得到最小的记录并与第二个记录进行位置交换；
    //          重复该过程，直到进行比较的记录只有一个时位置
    public static void selectSort(int[] array){
        if(array==null){
            return;
        }
        int length = array.length;
        int temp = 0;                   //存储值
        int flag = 0;                   //存储位置
        for(int i=0;i<length;i++){
            temp = array[i];
            flag = i;
            for(int j=i+1;j<length;j++){
                if(array[j]<temp){
                    flag = j;
                    temp = array[j];
                }
            }
           if(flag!=i){
               array[flag] = array[i];
               array[i] = temp;
           }
        }
    }
    public static void main(String[] args){
        int[] array = {5,4,9,8,7,6,0,1,3,2};
        selectSort(array);
        System.out.println("选择排序");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}