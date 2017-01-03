package com.neu.sort;

/**
 * 问题：如何进行归并排序
 * Created by lihongyan on 2015/10/31.
 */
public class MergeSort {
    //基本原理：对于给定的一组记录，首先将每两个相邻的长度为1的子序列进行归并，
    //          得到n/2个长度为2或1的子有序序列，再将其两两归并，反复执行此过程，
    //          直到得到一个有序序列
    public static void merge(int[] array,int begin,int middle,int end){
        int lLen = middle - begin + 1;
        int rLen = end - middle;
        int[] L = new int[lLen];
        int[] R = new int[rLen];
        int i,j,k;
        for(i=0,k=begin;i<lLen;i++,k++){
            L[i] = array[k];
        }
        for(j=0,k=middle+1;j<rLen;j++,k++){
            R[j] = array[k];
        }
        for(k=begin,i=0,j=0;i<lLen&&j<rLen;k++){
            if(L[i]<R[j]){
                array[k] = L[i];
                i++;
            }else{
                array[k] = R[j];
                j++;
            }
        }
        if(i<lLen){
            for(j=i;j<lLen;j++,k++){
                array[k] = L[j];
            }
        }
        if(j<rLen){
            for(i=j;i<rLen;i++,k++){
                array[k] = R[i];
            }
        }
    }
    public static void mergeSort(int[] array,int begin,int end){
        if(begin<end){
            int middle = begin + (end - begin)/2;
            mergeSort(array,begin,middle);
            mergeSort(array,middle+1,end);
            merge(array, begin, middle, end);
        }
    }
    public static void main(String[] args){
        int[] array = {5,4,9,8,7,6,0,1,3,2};
        mergeSort(array, 0, array.length - 1);
        System.out.println("归并排序");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}
