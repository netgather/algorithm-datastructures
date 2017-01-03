package com.neu.array;

/**
 * 问题:给定一个数组array,如果array[i]>array[j](i<j),那么array[i]与array[j]被称为一个反序对
 *     例如：给定数组{1,5,3,2,6}，共有(5,3)、(5,2)、(3,2)三组反序对
 * Created by lihongyan on 2015/10/30.
 */
public class ReverseCount {
    //方法一、蛮力法：对数组中的每一个数字，遍历它后面的所有数字，如果后面的数字比它小，那么就找到一个反序对
    //              由于算法采用了两重遍历，所以时间复杂度为O(n^2)。
    public static int reverseCount1(int[] array){
        if(array==null){
            return Integer.MIN_VALUE;
        }
        int result = 0;
        int length = array.length;
        for(int i=0;i<length;i++){
            for(int j=i+1;j<length;j++){
                if(array[j]<array[i]){
                    result++;
                }
            }
        }
        return result;
    }
    //方法二、分治归并法：可以参考归并排序的方法，在归并排序的基础上额外使用一个计数器来记录逆序数的个数，
    public static int count = 0;
    public static void merge(int[] array,int begin,int mid,int end){
        int i,j,k;
        int n1 = mid - begin + 1;
        int n2 = end - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for(i=0,k=begin;i<n1;i++,k++){
            L[i] = array[k];
        }
        for(i=0,k=mid+1;i<n2;i++,k++){
            R[i] = array[k];
        }
        for(k=begin,i=0,j=0;i<n1&&j<n2;k++){
            if(L[i]<R[j]){
                array[k] = L[i];
                i++;
            }else{
                array[k] = R[j];
                j++;
                count = count + mid - i + 1;
            }
        }
        if(i<n1){//左半数组未遍历完
            for(j=i;j<n1;j++,k++){
                array[k] = L[j];
            }
        }
        if(j<n2){//右半数组未遍历完
            for(i=j;i<n2;i++,k++){
                array[k] = R[i];
            }
        }
    }
    public static void merge_sort(int[] array,int begin,int end){
        if(begin<end){
            int mid = begin + (end - begin)/2;
            merge_sort(array,begin,mid);
            merge_sort(array,mid+1,end);
            merge(array,begin,mid,end);
        }
    }
    public static void main(String[] args){
        int[] array = {1,5,3,2,6};
        System.out.println(reverseCount1(array));
        merge_sort(array, 0, array.length - 1);
        System.out.print(count);
    }
}
