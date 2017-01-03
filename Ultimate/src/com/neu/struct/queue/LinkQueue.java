package com.neu.struct.queue;

/**
 * 问题：如何使用链表实现队列
 * Created by lihongyan on 2015/11/1.
 */
class QueueNode{
    public int data;
    public QueueNode next;
    public QueueNode(int data){
        this.data = data;
        this.next = null;
    }
}
public class LinkQueue<E> {

    private QueueNode head;
    private QueueNode tail;
    private int size;
    public LinkQueue(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    public boolean isEmpty(){
        if(head==null&&tail==null){
            return true;
        }else if(head==tail){
            return false;
        }else{
            return false;
        }
    }
    public void push(int data){
        QueueNode newNode = new QueueNode(data);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }
    public int pop(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }else{
            int data = head.data;
            head = head.next;
            size--;
            return data;
        }
    }
    public int peek(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }else{
            int data = head.data;
            return data;
        }
    }
    public int capacity(){
        QueueNode temp = getHead();
        int count = 0;
        while(temp!=null){
            count++;
            temp = temp.next;
        }
        return count;
    }
    public QueueNode getHead() {
        return head;
    }

    public static void main(String[] args) {
        LinkQueue lq = new LinkQueue();
        lq.push(1);
        lq.push(2);
        lq.push(3);
        System.out.println("队列长度为:" + lq.capacity());
        System.out.println("队首元素为:"+lq.pop());
        System.out.println("队首元素为:"+lq.pop());
        System.out.println("队列长度为:"+lq.capacity());
        System.out.println("队首元素为:"+lq.pop());
    }
}
