package com.neu.struct.stack;

import java.util.Stack;

/**
 * 问题：如何使用两个栈模拟队列操作
 *
 * Created by lihongyan on 2015/11/5.
 */
public class StackMoniQueue<E> {
       //方法：假设使用栈A与栈B模拟队列Q，A为插入栈，B为弹出栈，以实现队列Q。
       //     假设Ａ和Ｂ都为空，可以认为栈Ａ提供入队列的功能，栈Ｂ提供出队列的功能。
       //     要入队列，入栈Ａ即可；
       //     而出队列则需要分两种情况考虑：
       //       ①若栈Ｂ不为空，则直接弹出栈Ｂ的数据。
       //       ②若栈Ｂ为空，则依次弹出栈Ａ的数据，放入到栈Ｂ中，再弹出栈Ｂ的数据。
       //以上情况可以利用前面写的栈来实现，也可以采用java类库提供的Stack来实现，
    //使用java内置的Stack来实现
    private Stack<E> A = new Stack<E>();//插入栈
    private Stack<E> B = new Stack<E>();//弹出栈
    public synchronized boolean isEmpty(){
        if(A.empty()&&B.empty()){
            return true;
        }else{
            return false;
        }
    }
    public synchronized void push(E data){
        A.push(data);
    }
    public synchronized E pop(){
        if(B.isEmpty()){
            while(!A.isEmpty()){
                B.push(A.pop());
            }
        }
        return B.pop();
    }
    public static void main(String[] args){
        StackMoniQueue<Integer> smq = new StackMoniQueue<Integer>();
        smq.push(1);
        smq.push(2);
        smq.push(3);
        System.out.print("队首元素为:"+smq.pop());
    }
}
