package com.neu.beauty.struct.GetQueueMaxValue.method1;

import com.neu.beauty.struct.GetQueueMaxValue.QueueNode;
import com.neu.struct.stack.LinkStack;

public class GetQueueMaxValue {
    private QueueNode head = null;
    private QueueNode tail = null;
    public QueueNode getHead() {
        return head;
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
    public int capacity(){
        int count = 0;
        if(!isEmpty()){
            QueueNode temp = getHead();
            while(temp!=null){
                count++;
                temp = temp.next;
            }
            return count;
        }else{
            return count;
        }
    }
    public void push(int data){
        QueueNode newNode = new QueueNode(data);
        if(head==null&&tail==null){
            tail = head = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
    }
    public int pop(){
        if(head==null&&tail==null){
            return Integer.MIN_VALUE;
        }else if(head==tail){
            int data = head.data;
            head = tail = null;
            return data;
        }else{
            int data = head.data;
            head = head.next;
            return data;
        }
    }
    public int peek(){
        if(head==null&&tail==null){
            return Integer.MIN_VALUE;
        }else if(head==tail){
            return head.data;
        }else{
            return head.data;
        }
    }




    //方法一、使用传统的方式实现队列：利用一个数组或链表来存储队列的元素，
    //      利用两个指针分别指向队列的队首和队尾。如果采用这种方法，
    //      那么maxElement()操作需要遍历队列的所有元素，在队列长度为N的情况下，时间复杂度为O(N)。
    private int maxElement1(){
        int maxValue = Integer.MIN_VALUE;
        QueueNode temp = head;
        while(temp!=null){
            maxValue = maxValue>temp.data?maxValue:temp.data;
            temp = temp.next;
        }
        return maxValue;
    }









    //思考：按照空间换时间的思想，在方法一的基础上，多设置一个变量max，每入队一个元素，
    //     就更新一下max，是max总保存着队列中最大的元素，这样maxValue()函数只需要
    //     返回max的值就可以了，这一算法的时间复杂度为O(1)。
    private int max = Integer.MIN_VALUE;
    public void push2(int data){
        QueueNode newNode = new QueueNode(data);
        if(head==null&&tail==null){
            tail = head = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        if(data>max){
            max = data;
        }
    }
    private int maxElement2(){
        return this.max;
    }
    //缺点：假设要删除队列中的元素，并且当前要删除的元素恰好为队列中最大的元素。
    //     删除队列中最大的元素之后，max保存的值也应该删除，并更新max的为之前队列中次大的元素，
    //     但是现在max却为空。max只能使用一次，且有局限性。
    //解决：可以考虑增加一个栈(maxSpace)保存该队列中的最大值，每次向队列中插入元素时,
    //     判断插入的值是否大于maxSpace中的首部元素，如果大于，就将该值增加近maxSpace中
    //     当要删除队列中的元素时，判断该值是否与maxSpace首部元素相等，如果相等，则一并删除。
    //     此时，maxSpace中首部元素变为队列中次大的元素。
    private LinkStack<Integer> maxSpace = new LinkStack<Integer>();
    public void push3(int data){
        QueueNode newNode = new QueueNode(data);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        if(maxSpace.isEmpty()){
            maxSpace.push(data);
        }else{
            int temp = maxSpace.peek();
            if(data>temp){
                maxSpace.push(data);
            }
        }
    }
    public int pop3(){
        int data = Integer.MIN_VALUE;
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }else{
            data = head.data;
            head = head.next;
            int temp = maxSpace.peek();
            if(temp==data){
                maxSpace.pop();
            }
        }
        return data;
    }
    public int maxElement3(){
        return maxSpace.peek();
    }

    public static void main(String[] args){
        GetQueueMaxValue gqmv = new GetQueueMaxValue();
//        for(int i=0;i<10;i++){
//            gqmv.push1(i);
//        }
//        System.out.println(gqmv.maxElement1());

//        for(int i=0;i<10;i++){
//            gqmv.push2(i);
//        }
//        System.out.println(gqmv.maxElement2());
        for (int i=0;i<10;i++){
            gqmv.push3(i);
        }
        System.out.println(gqmv.maxElement3());
    }
}