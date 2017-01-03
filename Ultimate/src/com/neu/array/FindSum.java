package com.neu.array;

import java.util.Arrays;

/**
 * 问题：给定一个数组和一个整数，求这个数组中两两相加等于该整数的组合
 * Created by lihongyan on 2015/10/26.
 */
public class FindSum {
    //方法一、蛮力法：采用两重循环遍历数组来判断两个数的和是否为给定的数
    public static void findSum1(int[] array,int sum){
        int length = array.length;
        for(int i=0;i<length;i++){
            for(int j=i;j<length;j++){
                if((array[i]+array[j])==sum){
                    System.out.println(array[i]+" "+array[j]);
                }
            }
        }
    }
    //方法二、排序法：先对数组进行排序，然后对排序后的数组分别从前到后和从后到前遍历，
    //             假设从前往后遍历的下标为begin，从后往前遍历的下标为end，
    //             当满足array[begin]+end[end]<20时，这两个数一定在[begin+1,end]之间
    //             当满足array[begin]+end[end]>20时，这两个数一定在[bengin,end-1]之间
    public static void findSum2(int[] array,int sum){
        Arrays.sort(array);
        int length = array.length;
        int begin = 0;
        int end = length - 1;
        while(begin<end){
            if((array[begin]+array[end])<sum){
                begin++;
            }else if((array[begin]+array[end])>sum){
                end--;
            }else{
                System.out.println(array[begin]+" "+array[end]);
                begin++;
                end--;
            }
        }
    }
    public static void main(String[] args){
        int[] array = {1,7,17,2,6,3,14};
       // findSum1(array,20);
        findSum2(array,20);
    }
}
