package com.neu.sort;

/**
 * 问题：如何进行快速排序
 * Created by lihongyan on 2015/10/31.
 */
public class QuickSort {
    //基本原理：对于一组给定的记录，通过一趟排序后，将原序列分为两部分。
    //          其中，前一部分的所有记录均比后一部分的所有记录小。
    //          然后再依次对前后两部分的记录进行快速排序，递归该过程，
    //          直到序列中的所有记录均有序为止。
    //算法步骤：①分解。将输入的序列array[m...n]划分为两个非空子序列array[m...k]和array[k+1...n]
    //            使得array[m...k]中的任一元素的值不大于array[k+1...n]中任一元素的值
    //          ②递归求解。通过递归调用快速排序算法分别对array[m...k]和array[k+1...n]进行排序
    //          ③合并。由于对分解出的两个子序列的排序是就地进行的，所以在array[m...k]和array[k+1...n]
    //            都排好序后不需要执行任何计算array[m...n]就已排好序
    public static void sort(int[] array,int low,int high){
        if(low>=high){
            return;
        }
        int i = low;//i从左向右扫描
        int j = high;//j从右向左扫描
        int index = array[i];
        while(i<j){
            while(i<j&&index<=array[j]){
                j--;
            }
            if(i<j){
                array[i] = array[j];
                i++;
            }
            while(i<j&&index>=array[i]){
                i++;
            }
            if(i<j){
                array[j] = array[i];
                j--;
            }
        }
        array[j] = index;
        sort(array,low,j-1);
        sort(array,j+1,high);
    }
    public static void quickSort(int[] array){
        if(array==null){
            return;
        }
        int length = array.length;
        sort(array,0,length-1);
    }
    public static void main(String[] args) {
        int[] array = {5,4,9,8,7,6,0,1,3,2};
        quickSort(array);
        System.out.println("快速排序");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}
