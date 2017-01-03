package com.neu.array;

import java.util.Arrays;

/**
 * 问题：给定一个无序的数组，从数组中找出第K个最小的数
 * Created by lihongyan on 2015/10/26.
 */
public class GetKMin {
    //方法一、排序法：对数组进行排序，排序后的数组中第K-1个位置上的数字即为数组的第K个最小的数
    //             这种算法最好的时间复杂度为nlog(n)
    public static void getKMin1(int[] array,int k){
        Arrays.sort(array);
        System.out.println(array[k-1]);
    }
    //方法二、剪枝法：选一个数temp=array[n-1]作为枢纽，把比它小的数都放在它的左边，比它大的数都放在它的右边，
    //             然后判断temp的位置，如果它的位置为K-1，那么它就是第K个最小的数；如果它的位置小于K-1，
    //             那么说明第K小的数一定在数组的右半部分，采用递归的方法在数组的右半部分继续查找
    //             否则第K小的元素在数组的左半部分，采用递归的方法继续查找
    public static int quickSort(int[] array,int low,int high,int k){
        int i,j;
        int temp;
        if(low>high){
            return Integer.MIN_VALUE;
        }
        i = low + 1;
        j = high;
        temp = array[i];
        while(i<j){
            while(i<j&&array[j]>=temp){
                j--;
            }
            if(i<j){
                array[i++] = array[j];
            }
            while(i<j&&array[i]<temp){
                i++;
            }
            if(i<j){
                array[j--] = array[i];
            }
        }
        array[i] = temp;
        if(i+1==k){
            return temp;
        }else if(i+1>k){
            return quickSort(array,low,i-1,k);
        }else{
            return quickSort(array,i+1,high,k);
        }
    }
    public static int getKMin2(int[] array,int k){
        if(array==null||k>array.length){
            return Integer.MIN_VALUE;
        }
        return quickSort(array,0,array.length-1,k);
    }
    public static void main(String[] args){
        int[] array = {1,5,2,6,8,0,6};
        getKMin1(array,4);
        System.out.println(getKMin2(array,4));
    }
}
