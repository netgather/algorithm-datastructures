package com.neu.dsalgo.algorithm;

/**
 * 问题：使用Java实现斐波那契数列
 * Created by lihongyan on 2015/10/27.
 */
public class Fibonacci {

    public static int fibonacci(int n){
       if(n<=2){
           return 1;
       }else{
           return fibonacci(n-1) + fibonacci(n-2);
       }
    }
    public static void main(String[] args){
        int result = 0;
        for(int i=1;i<=5;i++){
            result += fibonacci(i);
            System.out.print(result+" ");
        }
        System.out.println();
        System.out.println(result);
    }
}
