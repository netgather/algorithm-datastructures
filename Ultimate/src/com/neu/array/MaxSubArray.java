package com.neu.array;

/**
 * 问题：一个有n个元素的数组，这n个元素可以是整数也可以是负数，数组中连续性的一个或多个元素可以组成一个连续的子数组，
 *      一个数组可能有多个这种连续的子数组求子数组和的最大值。
 * Created by lihongyan on 2015/10/24.
 */
public class MaxSubArray {
    //方法一、蛮力法：找出所有的子数组，然后求出子数组的和，在所有子数组的和中取最大值
    public static int maxSubArray1(int[] array){
        int length = array.length;
        int maxSum = 0;
        int thisSum = 0;
        int i,j,k;
        for(i=0;i<length;i++){
            for(j=i;j<length;j++){
                thisSum = 0;
                for(k=i;k<j;k++){
                    thisSum += array[k];
                }
                if(thisSum>maxSum){
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }
    //方法二、重复利用已经计算的子数组 Sum[i,j] = Sum[i,j-1] + array[j],省去计算Sum[i,j]的时间
    public static int maxSubArray2(int[] array){
        int length = array.length;
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<length;i++){
            int sum = 0;
            for(int j=i;j<length;j++){
                sum += array[j];
                if(sum>maxSum){
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }
    //动态规划、首先可以根据数组的最后一个元素array[length-1]与最大子数组的关系分为以下三种情况：
    //      1)最大子数组包含array[length-1],即以array[length-1]结尾
    //      2)array[length-1]单独构成最大子数组
    //      3)最大子数组不包含array[length-1]，那么求array[1,...,length-1]的最大子数组可以转换为求array[1,...length-2]的最大子数组
    public static int max(int x,int y){
        return x>y?x:y;
    }
    public static int maxSubArray3(int[] array){
        int length = array.length;
        int[] end = new int[length];
        int[] all = new int[length];
        end[length-1] = array[length-1];
        all[length-1] = array[length-1];
        end[0] = all[0] = array[0];
        for(int i=1;i<length;i++){
            end[i] = max(end[i-1]+array[i],array[i]);
            all[i] = max(end[i],all[i-1]);
        }
        return all[length-1];
    }
    //优化的动态规划法
    public static int maxSubArray4(int[] array){
        int length = array.length;
        int nAll = array[0];
        int nEnd = array[0];
        for(int i=1;i<length;i++){
            nEnd = max(nEnd+array[i],array[i]);
            nAll = max(nEnd,nAll);
        }
        return nAll;
    }
    //确定最大子数组的位置
    private static int begin = 0;
    private static int end = 0;
    public static int maxSubArray5(int[] array){
        int maxSum = Integer.MIN_VALUE;
        int nSum = 0;
        int nStart = 0;
        for (int i=0;i<array.length;i++){
            if(nSum<0){
                nSum = array[i];
                nStart = i;
            }else{
                nSum += array[i];
            }
            if(nSum>maxSum){
                maxSum = nSum;
                begin = nStart;
                end =i;
            }
        }
        return maxSum;
    }
    public static void main(String[] args){
        int[] array = {1,-2,4,8,-4,7,-1,-5};
        System.out.println(maxSubArray1(array));
        System.out.println(maxSubArray2(array));
        System.out.println(maxSubArray3(array));
        System.out.println(maxSubArray4(array));
        System.out.println(maxSubArray5(array));
        System.out.println(begin+" "+end);

    }
}
