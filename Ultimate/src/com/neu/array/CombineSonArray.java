package com.neu.array;

/**
 * 问题:数组array[0,mid-1]和array[mid,n-1]是各自有序的,对数组array[0,n-1]的两个子有序段进行合并,得到array[0,n-1]整体有序
 *     要求空间复杂度为O(1),假设array[i]是支持'<'运算符的,假设给定数组array[1,5,6,7,9,2,4,8,10,13,14]
 *     mid=5,array[0]~array[4]是有序的,array[5]~array[10]是有序的,合并后的数组为{1,2,4,5,6,7,8,9,10,13,14}
 * Created by lihongyan on 2015/10/30.
 */
public class CombineSonArray {
    //实现思路:首先，遍历数组中下标为0~mid-1的元素，将遍历到的元素的值与array[mid]进行比较,当遍历到array[i](0<=i<=mid-1)时，
    //             如果满足array[i]>array[mid],那么交换array[i]与array[mid]的值
    //        接着，找到交换后的array[mid](array[i])在array[mid,n-1]中的具体位置(在array[mid,n-1]中进行插入排序)
    //             实现方法为:遍历array[mid,n-2],如果array[mid+1]<array[mid],那么交换array[mid]与array[mid+1]的位置
    //mid代表下标
    public static void findRightPlaceForMid(int[] array,int mid){
        int length = array.length;
        int temp;
        for(int i=mid;i<length-1;i++){
            if(array[i]>array[i+1]){
                temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
            }
        }
    }
    public static void combineSonArray(int[] array,int mid){
        int temp;
        for(int i=0;i<mid;i++){
            if(array[i]>array[mid]){
                temp = array[i];
                array[i] = array[mid];
                array[mid] = temp;
                findRightPlaceForMid(array,mid);
            }
        }
    }
    public static void main(String[] args){
        int[] array = {1,5,6,7,9,2,4,8,10,13,14};
        combineSonArray(array,5);
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}
