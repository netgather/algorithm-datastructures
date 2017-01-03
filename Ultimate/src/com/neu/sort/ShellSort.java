package com.neu.sort;

/**
 * 问题：如何进行希尔排序
 * Created by lihongyan on 2015/10/31.
 */
public class ShellSort {
    //基本原理：先将待排序的数组元素分成多个子序列，使得每个子序列的元素个数相对较少，
    //        然后对各个子序列分别进行直接插入排序，待整个待排序序列“基本有序后”，
    //        最后在对所有元素进行一次直接插入排序
    //算法步骤：①选择一个步长序列t(1),t(2),...,t(k),满足t(i)>t(j)(i<j),t(k)=1。
    //        ②按步长序列个数k，对待排序序列进行k趟排序。
    //        ③每趟排序，根据对应的步长t(i)，将待排序序列分割成t(i)个子序列，
    //         分别对各个子序列进行直接插入排序
    //注释：当步长为1是，所有元素作为一个序列来处理。
    public static void shellSort(int[] array){
        if(array==null){
            return;
        }
        int length = array.length;
        int i,j;
        int h;
        int temp;
        for(h=length/2;h>0;h=h/2){//定义步长序列，根据序列长度确定遍历次数
            for(i=h;i<length;i+=h){//进行直接插入排序
                temp = array[i];
                j = i;
                if(array[j-h]>temp){
                    while(j>=h&&array[j-h]>temp){
                        array[j] = array[j-h];
                        j-=h;
                    }
                }
                array[j] = temp;
            }
        }
    }
    public static void main(String[] args){
        int[] array = {5,4,9,8,7,6,0,1,3,2};
        shellSort(array);
        System.out.println("希尔排序");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}
