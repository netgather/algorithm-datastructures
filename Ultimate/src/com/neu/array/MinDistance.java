package com.neu.array;

/**
 * 问题:给定一个数组，数组中含有重复元素，给出两个数n1和n2,求这两个数在数组中所出现位置的最下距离
 *     例如:在{4,5,6,4,7,4,6,4,7,8,5,6,4,3,10,8}中，4和8之间的最小距离为2
 * Created by lihongyan on 2015/10/29.
 */
public class MinDistance {
    //实现思路:遍历数组，会遇到以下两种情况。
    //       ①当遇到n1时,记录下n1值对应的数组下表位置n1_index,通过求n1_index与上次遍历到n2的下标值n2_index的差，
    //        可以求出最近一次遍历到的n1与n2的距离
    //       ②当遇到n2时，同样记录下它在数组中下标的位置n2_index,然后通过求n2_index与上次遍历到n1的下标值n1_index的差，
    //        求出最近一次遍历到的n1与n2的距离
    //       ③定义一个变量min_dist记录n1与n2的最小距离，在以上两种情况下，每次求出n1与n2的距离后与min_dist相比，求出最小值
    //算法分析:只需要对数组进行一次遍历就可以求出最小距离,因此时间复杂度为O(n)。
    public static int min(int a,int b){
        return a>b?b:a;
    }
    public static int minDistence(int[] array,int n1,int n2){
        if(array==null){
            return Integer.MIN_VALUE;
        }
        int length = array.length;
        int n1_length = -1;
        int n2_length = -1;
        int min_dist = Integer.MIN_VALUE + 1;
        for(int i=0;i<length;i++){
            if(array[i]==n1){
                n1_length = i;
                if(n2_length>0){
                    min_dist = min(Math.abs(min_dist),Math.abs(n1_length-n2_length));
                }
            }
            if(array[i]==n2){
                n2_length = i;
                if(n1_length>0){
                    min_dist = min(Math.abs(min_dist),Math.abs(n1_length-n2_length));
                }
            }
        }
        return min_dist;
    }
    public static void main(String[] args){
        int[] array = {4,5,6,4,7,4,6,4,7,8,5,6,4,3,10,8};
        System.out.print(minDistence(array,4,8));
    }
}
