package com.neu.string;

/**
 * 问题：如何删除字符串中重复的字符
 *      删除字符串中重复的字符，例如，“good”去掉重复的字符编程“god”
 * Created by lihongyan on 2015/10/31.
 */
public class RemoveDuplicate {
    //方法一、蛮力法：把给定的字符看做一个字符数组，对该数组使用双循环进行遍历，
    //             如果发现有重复字符，就把该字符设置为'\0'，最后再把这个字符数组中的所有'\0'去掉，
    //             此时得到的字符串就是删除重复字符的目标字符串。
    public static String removeDuplicate1(String str){
        if(str==null){
            return null;
        }
        char[] chars = str.toCharArray();
        int length = chars.length;
        for(int i=0;i<length;i++){
            if(chars[i]=='\0'){
                continue;
            }
            for(int j=i+1;j<length;j++){
                if(chars[j]=='\0'){
                    continue;
                }
                if(chars[i]==chars[j]){
                    chars[j] = '\0';
                }
            }
        }
        int n = 0;
        for(int i=0;i<length;i++){
            if(chars[i]!='\0'){
                chars[n] = chars[i];
                n++;
            }
        }
        return new String(chars,0,n);
    }
    //方法二、空间换时间：由于常见的字符只有256个，可以假设这道题字符串中不同的字符个数最多为256个，
    //                那么可以申请一个大小为256的int类型的数组来记录每个字符出现的次数，初始化都为0，
    //                把这个字符的编码作为数组的下标，在遍历字符数组时，如果这个字符出现的次数为0，当出现该字符时把它置为1；
    //                如果这个字符出现的次数为1，说明这个字符在前面已经出现过了，就可以把这个字符置为'\0'，
    //                最后去掉所有'\0',就实现了去重的目的。
    //       因为一个字符占用1bit，而一个整型数占用32bit，所以只需要申请一个大小为8的int型数组即可
    public static String removeDuplicate2(String str){
        if(str==null){
            return null;
        }
        char[] buffer = str.toCharArray();
        int length = buffer.length;
        int[] flag = new int[256];
        for(int i=0;i<8;i++){
            flag[i] = 0;
        }
        for(int i=0;i<length;i++){
            int index = (int)buffer[i]/32;
            int shift = (int)buffer[i]%32;
            int temp = flag[index]&(1<<shift);
            if(temp!=0){
                buffer[i] = '\0';
            }
            flag[index] = flag[index]|(1<<shift);
        }
        int n = 0;
        for(int i=0;i<length;i++){
            if(buffer[i]!='\0'){
                buffer[n++] = buffer[i];
            }
        }
        return new String(buffer,0,n);
    }
    public static void main(String[] args){
        String str = "abcaabcd";
        str = removeDuplicate1(str);
        System.out.println(str);
        System.out.println(removeDuplicate2(str));
    }
}
