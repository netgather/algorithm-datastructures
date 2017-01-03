package com.neu.dsalgo.algorithm;

/**
 * 问题：使用Java实现汉若塔问题求解
 * Created by lihongyan on 2015/10/27.
 */
public class Hanoi {
    public static void hanoi(String A,String B,String C,int n){
        if(n==1){
            System.out.println("Move disk " + n + " from " +A+ " to " + C);
        }else{
            hanoi(A,C,B,n-1);
            System.out.println("Move disk " + n + " from " + A + " to " + C);
            hanoi(B,A,C,n-1);
        }
    }
    public static void main(String[] args){
        hanoi("A","B","C",3);
    }
}
