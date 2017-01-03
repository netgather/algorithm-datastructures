package com.neu.util;

/**
 * 功能：将给定的值转化为该数的二进制形式表示
 * Created by lihongyan on 2015/11/6.
 */
public class ToBinary {

    public static byte[] toBinary(int value){
        String str = Integer.toBinaryString(value);
        int length = str.length();
        byte[] binary = new byte[length];
        for(int i=0;i<length;i++){
            binary[i] = (byte)(str.charAt(i) - '0');
        }
        return binary;
    }
    public static byte[] toBinary(char value){
        String str = Integer.toBinaryString((int)value);
        int length = str.length();
        byte[] binary = new byte[length];
        for(int i=0;i<length;i++){
            binary[i] = (byte)(str.charAt(i) - '0');
        }
        return binary;
    }
    public static byte[] toBinary(byte value){
        String str = Integer.toBinaryString((int)value);
        int length = str.length();
        byte[] binary = new byte[length];
        for(int i=0;i<length;i++){
            binary[i] = (byte)(str.charAt(i) - '0');
        }
        return binary;
    }
}
