package com.neu.string;

import java.util.Arrays;

/**
 * 问题：如何判断两个字符是否由相同的字符组成
 *      由相同的字符组成是指组成两个字符串的字母以及各字母的个数是一样的，只是排列顺序不同而已
 * Created by lihongyan on 2015/10/31.
 */
public class Compare {
    //方法一、排序法：对两个字符串进行排序，比较两个排序后的字符串是否相等。
    //             若相等，则表明他们是有相同的字符组成的。否则，则报名它们是有不同的字符组成的
    //分析：该算法的时间复杂度取决于排序算法的时间复杂度，由于最快的排序算法的时间复杂度为O(nlogn),
    //     因此，该方法的时间复杂度不低于O(nlogn)。
    public static boolean compare1(String str1,String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        String strTemp1 = new String(chars1);
        String strTemp2 = new String(chars2);
        if(strTemp1.equals(strTemp2)){
            return true;
        }else{
            return false;
        }
    }
    //方法二、空间换时间：假设字符串中只使用ASCII字符，由于ASCII字符共有266个，
    //                在事先是可以通过申请大小为266的数组来记录各个字符出现的个数，
    //                并初始化为0，然后遍历第一个字符，将字符串中字符对应的ASCII码值作为数组下标，
    //                把对应数组的元素加1，然后遍历第二个字符串，把数组中对应的元素值-1，
    //                如果最后数组中各个元素中都为0，说明这两个字符串是由相同的字符组成，
    //                否则说明这两个字符串是由不同的字符组成。
    public static boolean compare2(String str1,String str2){
        byte[] bytes1 = str1.getBytes();
        byte[] bytes2 = str2.getBytes();
        int[] buffer = new int[256];
        for(int i=0;i<buffer.length;i++){
            buffer[i] = 0;
        }
        for(int i=0;i<bytes1.length;i++){
            buffer[bytes1[i]-'0']++;
        }
        for(int i=0;i<bytes2.length;i++){
            buffer[bytes2[i]-'0']--;
        }
        for(int i=0;i<buffer.length;i++){
            if(buffer[i]!=0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        String str1 = "aaaabbc";
        String str2 = "abcbaaa";
        System.out.println(compare1(str1, str2));
        System.out.println(compare2(str1, str2));
    }

}
