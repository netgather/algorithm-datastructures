package com.neu.array;

/**
 * 问题：一个整型数组里除了一个数字之外，其他数字都出现了两次，找出这个只出现1次的数字,
 *      要求时间复杂度为O(n),空间复杂度为O(1)
 * Created by lihongyan on 2015/10/26.
 */
public class FindNotDouble {
    //方法一、题目强调只有一个数字出现1次，其他数字都出现了2次，首先想到的是异或运算，根据异或运算的定义可知：
    //      任何一个数字异或它自己都等于0。所以，如果从头到尾依次异或数组中的每一个数字，那些出现两次的数字
    //      全部在异或中被抵消掉，最终的结果刚好是这个只出现1次的数字
    public static int findNotDouble(int[] array){
        int length = array.length;
        int result = array[0];
        for(int i=1;i<length;i++){
            result = result ^ array[i];
        }
        return result;
    }
    //引申:如果题目改为：一个整形数组里除了一个数字之外，其他数字都出现了3次，那么如何找出只出现一次的数字
    //如果数组中的所有数都出现n次，那么这个数组中的所有对应的二进制数中，各个位上的1出现的个数均可以被n整除
    public static int findOnce(int[] array,int appearTime){
        int length = array.length;
        int[] bitCount = new int[32];
        //计算数组中所有数字对应的二进制数各个位置上出现1的次数
        for(int i=0;i<length;i++){
            for(int j=0;j<32;j++){
                bitCount[j] = bitCount[j] + ((array[i]>>j)&1);
            }
        }
        int appearOne = 0;
        for(int i=0;i<32;i++){
            if(bitCount[i]%appearTime!=0){
                appearOne = appearOne + (1<<i);
            }
        }
        return appearOne;
    }
    public static void main(String[] args){
        int[] array = {1,2,3,2,4,3,5,4,1};
        System.out.println(findNotDouble(array));
        int[] temp = {1,2,1,2,4,2,4,4,1,7};
        System.out.println(findOnce(temp,3));
    }
}
