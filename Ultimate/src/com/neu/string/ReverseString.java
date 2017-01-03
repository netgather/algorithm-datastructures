package com.neu.string;

/**
 * 问题：如何实现字符串的反转
 * Created by lihongyan on 2015/10/31.
 */
public class ReverseString {
    //方法、实现字符串的反转只需要进行两次反转即可，第一次对整个字符串中的字符进行反转；
    //     通过第一次的反转已经实现了单次顺序的反转，只不过每个单次中字符的顺序反了，
    //     接下来只需要对每个单次进行字符反转即可得到想要的结果。
    public static void swap(char[] chars,int begin,int end){
        char temp;
        while(begin<end){
            temp = chars[end];
            chars[end] = chars[begin];
            chars[begin] = temp;
            begin++;
            end--;
        }
    }
    public static String reverseString(String str){
        if(str==null){
            return null;
        }
        char[] chars = str.toCharArray();
        int length = chars.length;
        swap(chars,0,length-1);
        int begin = 0;
        for(int i=0;i<length;i++){
            if(chars[i]==' '){
                swap(chars,begin,i-1);
                chars[i] = ' ';
                begin = i + 1;
            }
        }
        swap(chars,begin,length-1);
        return new String(chars);
    }
    public static void main(String[] args){
        String str = "Hello World";
        str = reverseString(str);
        System.out.print(str);
    }
}
