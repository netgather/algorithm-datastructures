package com.neu.struct.stack;

/**
 * 问题：如何使用链表实现栈
 * Created by lihongyan on 2015/11/1.
 */
class StackNode<E>{
    public E data;
    public StackNode<E> next;
    public StackNode(E data){
        this.data = data;
        next = null;
    }
}
public class LinkStack<E> {

    private int size;
    private StackNode<E> top;
    public LinkStack(){
        this.size = 0;
        this.top = null;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void push(E data){
        StackNode newNode = new StackNode(data);
        if(isEmpty()){
            top = newNode;
        }else{
            newNode.next = top;
            top = newNode;
        }
        size++;
    }
    public E pop(){
        if(isEmpty()){
            return null;
        }else{
            E data = top.data;
            top = top.next;
            size--;
            return data;
        }
    }
    public E peek(){
        if(size==0){
            return null;
        }else{
            E data = top.data;
            return data;
        }
    }
    public int getSize() {
        return this.size;
    }
    public static void main(String[] args){
        LinkStack<Integer> ls = new LinkStack<Integer>();
        ls.push(1);
        ls.push(2);
        System.out.println("栈大小为:" + ls.getSize());
        System.out.println("栈顶元素为:" + ls.pop());
        System.out.println("栈大小为:" + ls.getSize());
        System.out.println("栈顶元素为:"+ls.peek());
        System.out.println("栈大小为:" + ls.getSize());
        System.out.println("栈顶元素为:" + ls.pop());
        System.out.println("栈大小为:" + ls.getSize());
    }
}
