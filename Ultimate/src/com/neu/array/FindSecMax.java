package com.neu.array;

/**
 * 问题：如何寻找数组中第二大的数
 * 解析：先定义两个变量：一个变量用来存储数组中的最大数，初始值为数组首元素，
 *      另一个变量用来存储数组元素的第二大数，初始值为最小负整数，然后遍历数组
 *      如果数组元素的值比最大数变量的值大，则将第二大数变量的值更新为最大数变量的值
 *      最大数变量的值更新为该数组元素的值，若数组元素的值比最大值变量小，则判断该数组元素的值是否
 *      比第二大数的值大；若数组元素的值比第二大数的值大，则更新第二大数的值为该数组元素的值
 * Created by lihongyan on 2015/10/24.
 */
public class FindSecMax {
    public static int findSecMax(int[] array){
        int length = array.length;
        int max = array[0];
        int secMax = Integer.MIN_VALUE;
        for(int i=1;i<length;i++){
            if(array[i]>max){
                secMax = max;
                max = array[i];
            }else{
                if(array[i]>secMax){
                    secMax = array[i];
                }
            }
        }
        return secMax;
    }
    public static void main(String[] args){
        int[] a = {7,3,19,40,4,7,1};
        System.out.print(findSecMax(a));
    }
}
