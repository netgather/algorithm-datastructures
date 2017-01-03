package com.neu.array;

/**
 * 问题：如何使用递归的方法求一个整数数组的最大元素
 * Created by lihongyan on 2015/10/29.
 */
public class MaxNum {
    //递归方法求解的主要思路：递归的求解“数组第一个元素”与“数组中其他元素组成的子数组的最大值”的最大值
    private static int max(int a,int b){
        return a>b?a:b;
    }
    public static int maxnum(int[] array,int begin){
        int length = array.length - begin;
        if(length==1){
            return array[begin];
        }else{
            return max(array[begin],maxnum(array, begin + 1));
        }
    }
    public static void main(String[] args){
        int[] array = {0,16,2,3,4,5,10,7,8,9};
        System.out.print(maxnum(array,0));
    }
}
