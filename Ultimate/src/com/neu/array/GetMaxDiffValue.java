package com.neu.array;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 问题：数组中的一个数字减去它邮编自数组中的一个数字可以得到一个差值，
 *      求所有可能的差值中的最大值。
 * Created by lihongyan on 2015/10/29.
 */
public class GetMaxDiffValue {
    //方法一、蛮力法：首先，遍历数组，找到所有可能的差值；其次，从所有差值中找出最大值。
    //             具体实现方法为：针对数组array中的每个元素array[i](0<i<n-1)，
    //             求所有array[i] - array[j](i<j<n-1)的值中的最大值。
    public static int getMaxDiffValue1(int[] array){
        int length = array.length;
        if(length==0){
            return Integer.MIN_VALUE;
        }
        if(length==1){
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<length;i++){
            for(int j=i+1;j<length;j++){
                if(array[i]-array[j]>max){
                    max = array[i] - array[j];
                }
            }
        }
        return max;
    }
    //方法二、二分法(通过二分法可以减少计算次数)：把数组分为两个子数组，那么最大的差值只能有3中情况:
    //         ①最大的差值对应的被减数和减数都在左子数组中，假设最大差值为leftMax;
    //         ②最大的差值对应的被减数和减数都在右子数组中，假设最大差值为rightMax;
    //         ③被减数是左子数组的最大值，减数是右子数组中的最小值，假设差值为minMax。
    //         那么就可以得到这个数组的最大差值为这三个差值的最大值，即max(leftMax,rightMax,minMax)
    public static int getMax(int[] array){

        int length = array.length;
        int result = 0;
        if(array==null){
            return Integer.MIN_VALUE;
        }
        if(length<=1){
            return Integer.MIN_VALUE;
        }
        AtomicInteger max = new AtomicInteger(0);
        AtomicInteger min = new AtomicInteger(0);
        result = getMaxDiffValue2(array,0,length-1,max,min);
        return result;
    }
    public static int getMaxDiffValue2(int[] array,int begin,int end,AtomicInteger max,AtomicInteger min){
        if(begin==end){
            max.set(array[end]);
            min.set(array[begin]);
            return Integer.MIN_VALUE;
        }
        int middle = begin + (end - begin)/2;
        //数组前半部分的最大值与最小值
        AtomicInteger lMax = new AtomicInteger(0);
        AtomicInteger lMin = new AtomicInteger(0);
        //数组前半部分的最大差值(第一种情况)
        int leftMax = getMaxDiffValue2(array,begin,middle,lMax,lMin);

        //数组后半部分的最大值与最小值
        AtomicInteger rMax = new AtomicInteger(0);
        AtomicInteger rMin = new AtomicInteger(0);
        //数组后半部分的最大差值(第二种情况)
        int rightMax = getMaxDiffValue2(array,middle+1,end,rMax,rMin);

        //第三种情况
        int mixMax = lMax.get() - rMin.get();

        //求数组的最大值与最小值
        if(lMax.get()>rMax.get()){
            max.set(lMax.get());
        }else{
            max.set(rMax.get());
            if(lMin.get()<rMin.get()){
                min.set(lMin.get());
            }else{
                min.set(rMin.get());
            }
        }
        //求最大的差值
        int allMax = (leftMax>rightMax)?leftMax:rightMax;
        int temp = (allMax>mixMax)?allMax:mixMax;
        return temp;
    }
    //方法三、动态规划：

    public static void main(String[] args){
        int[] array = {1,4,17,3,2,9};

        System.out.println(getMaxDiffValue1(array));
        System.out.println(getMax(array));
    }
}
