package com.neu.array;

/**
 * 问题:如何求指定数字在数组中第一次出现的位置
 * 例如:给定数组array={3,4,5,6,5,6,7,8,9,8},这个数组中相邻元素之差都为1，给定数字9，它在数组中第一次出现的位置下表为8
 * Created by lihongyan on 2015/10/29.
 */
public class FindIndex {
    //方法一、蛮力法：假设指定数字为t,顺序遍历数组中的每一个元素，并且将数组中的元素与t进行比较,判断两个值是否相等
    //             若相等,则返回下标位置,遍历完数组还没有找到t,则说明t在数组中不存在,返回-1
    //       分析:该算法的时间复杂的为O(n)
    //方法二、跳跃搜索法:从数组第一个元素开始(i=0),把数组当前位置的值与t进行比较,如果相等,则返回数组下标,
    //                否则,从数组下标为i+|t-array[i]|处继续查找。
    public static int findIndex(int[] array,int value){
        if(array==null){
            return Integer.MIN_VALUE;
        }
        int length = array.length;
        int i = 0;
         while(i<length){
             if(array[i]==value){
                 return i;
             }else{
                 i = i + Math.abs(value - array[i]);
             }
         }
        return -1;
    }
    public static void main(String[] args){
        int[] array = {3,4,5,6,5,6,7,8,9,8};
        System.out.println(findIndex(array,9));
    }
}
