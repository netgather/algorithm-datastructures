package com.neu.struct.stack;

import java.util.Arrays;

/**
 * 问题：如何使用数组实现栈
 * Created by lihongyan on 2015/11/1.
 */
public class ArrayStack<E>{

    private Object[] array;
    private int size;
    public ArrayStack(){
        this.array = new Object[10];
        this.size = 0;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void push(E item){
        ensureCapacity(size+1);
        array[size++] = item;
    }
    public E pop(){
        if(isEmpty()){
            return null;
        }else{
            E item = (E)array[--size];
            return item;
        }
    }
    public E peek(){
        if(isEmpty()){
            return null;
        }else{
            return (E)array[size-1];
        }
    }
    private void ensureCapacity(int size){
        int length = this.array.length;
        if(size>length){
            int newLength = 10;
            this.array = Arrays.copyOf(this.array,newLength);
        }else{
            return;
        }
    }

    public static void main(String[] args){
        ArrayStack<Integer> as = new ArrayStack<Integer>();
        as.push(1);
        as.push(2);
        System.out.println("栈大小为:" + as.size);
        System.out.println("栈顶元素为:"+as.pop());
        System.out.println("栈大小为:" + as.size);
        System.out.println("栈顶元素为:" + as.peek());
        System.out.println("栈大小为:" + as.size);
    }
}
