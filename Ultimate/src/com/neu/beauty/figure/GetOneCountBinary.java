package com.neu.beauty.figure;


import com.neu.util.ToBinary;

/**
 * 问题：求二进制中1的个数
 *      对于一个字节(8bit)的无符号整型变量，求其二进制表示中“1”的个数，要求算法的执行效率尽可能高
 *      10100010
 * Created by lihongyan on 2015/11/6.
 */
public class GetOneCountBinary {
    //方法一、对于二进制操作，除以一个2，原来的数字将会减少一个0.如果除的过程中有余数，
    //      那么就表示当前位置有一个1.以10100010为例，第一除以2时，商为1010001，余数为0.
    //      第二次除以2时，商为101000，余数为1。因此，可以考虑利用整型数据除法的特点，
    //      通过相除和判断余数的值来分析
    public int getOneCount1(int value){
        int count = 0;
        byte[] binary = ToBinary.toBinary(value);
        int length = binary.length;
        for(int i=length-1;i>=0;i--){
            if(binary[i]%2==1){
                count++;
            }else{
                continue;
            }
        }
        return count;
    }
    //方法二、对比前面的代码，向右移位操作同样可以达到相除的目的。唯一不同之处在于，
    //      移位之后如何判断是否有1存在？
    //      对于这个问题，可以使用“与(&)”操作符判断末位是否为“1”。
    //      可以把给定的数字与“00000001”进行“与”操作，如果结果为“1”，则表示当前数字的末位为“1”。
    //分析：算法的时间复杂度为O(logv),其中logv为二进制的位数
    public int getOneCount2(int value){
        int count = 0;
        byte[] binary = ToBinary.toBinary(value);
        int length = binary.length;
        for(int i=length-1;i>=0;i--){
            if((binary[i]&1)==1){
                count++;
            }else{
                continue;
            }
        }
        return count;
    }
    //方法三：让算法的时间复杂度只与“1”的个数有关
    //       假设给定的二进制数中只有一个1的情况。那么如何判断给定的二进制数中有且只有一个1？
    //       可以通过判断这个数是否是2的整数次幂来实现。另外，如果只和这一个“1”进行判断，
    //       如何设计操作？如果进行这个操作，结果为0或1。如果希望操作后的结果为0,
    //       01000000可以和00111111进行“与”操作
    public int getOneCount3(int value){
        int count = 0;
        byte[] binary = ToBinary.toBinary(value);
        int length = binary.length;
        for(int i=length-1;i>=0;i--){
            if((binary[i]^(binary[i]-1))==1){
                count++;
            }else{
                continue;
            }
        }
        return count;
    }
    public static void main(String[] args){
        GetOneCountBinary gocb = new GetOneCountBinary();
        System.out.println(gocb.getOneCount1(162));
        System.out.println(gocb.getOneCount2(162));
        System.out.println(gocb.getOneCount3(162));
    }
}
