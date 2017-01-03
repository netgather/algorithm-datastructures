package com.neu.array;

/**
 * 问题：寻找数组中的最大值与最小值
 * 方法一：问题分解法
 * 方法二：取单元素法
 * 方法三：去双元素法
 * Created by lihongyan on 2015/10/24.
 */
public class GetMaxAndMin {

    private static int max;
    private static int min;
    public static void getMaxAndMin(int[] array){
        min = array[0];
        max = array[0];
        int length = array.length;
        for(int i=1;i<length-1;i++){
            if(array[i]>array[i+1]){
                if(array[i]>max){
                    max = array[i];
                }
                if(array[i+1]<min){
                    min = array[i+1];
                }
            }
            if(array[i]<array[i+1]){
                if(array[i+1]>max){
                    max = array[i+1];
                }
                if(array[i]<min){
                    min = array[i];
                }
            }
        }
    }
    public static void main(String[] args){
        int[] array = {7,3,19,40,4,7,1};
        getMaxAndMin(array);
        System.out.println(max);
        System.out.print(min);
    }
}
