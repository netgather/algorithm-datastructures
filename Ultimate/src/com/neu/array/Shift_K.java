package com.neu.array;

/**
 * 问题：把一个数组循环右移K位
 * Created by lihongyan on 2015/10/26.
 */
public class Shift_K {
    public static void reverse(int[] array,int begin,int end){
        for( ;begin<end;begin++,end--){
            int temp = array[end];
            array[end] = array[begin];
            array[begin] = temp;
        }
    }
    public static void shift_K(int[] array,int k){
        int length = array.length;
        k = k % length;
        reverse(array,length-k,length-1);
        reverse(array,0,length-k-1);
        reverse(array,0,length-1);
    }
    public static void main(String[] args){
        int[] array = {1,2,3,4,5,6,7,8};
        shift_K(array,3);
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}
