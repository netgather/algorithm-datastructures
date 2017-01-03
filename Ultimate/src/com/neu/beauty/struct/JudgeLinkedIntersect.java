package com.neu.beauty.struct;

/**
 * 问题：判断两个链表是否相交
 * 给出两个单项链表的头指针，判断这两个链表是否相交（假设两个链表均不带环）
 * 这样一个问题，也许我们平时很少考虑。但在一个大的系统中，如果出现两个链表相交的情况，
 * 一旦释放了其中一个链表的所有节点，那样就会造成信息丢失，并且与之相交的链表也会受到影响。
 * 在特殊情况下，的确需要出现相交的两个链表，我们希望在释放一个链表之前知道是否有其他链表
 * 跟当前这个链表相交。
 * Created by lihongyan on 2015/11/16.
 */
public class JudgeLinkedIntersect {
    /*
    * 方法一：直观的想法
    *       判断第一个链表的每个节点是否在第二个链表中
    * 复杂度：
    *       时间复杂度：O(length(h1)*length(h2))
    * */
    private class User{
        private int id;
        private String name;
        public User(){}
        public User(int id,String name){
            this.id = id;
            this.name = name;
        }
        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
    }
    private class Node{
        private User user;
        private Node next;
        public Node(){}
        public Node(User user){
            this.user = user;
            this.next = null;
        }
        public User getUser() {return user;}
        public void setUser(User user) {this.user = user;}
        public Node getNext() {return next;}
        public void setNext(Node next) {this.next = next;}
        public boolean equals(Node other) {
            boolean nameEqual = this.user.getName().equalsIgnoreCase(other.getUser().getName());
            boolean idEqual = (this.user.getId()==other.getUser().getId())?true:false;
            if(nameEqual&&idEqual){
                return true;
            }else{
                return false;
            }
        }

    }
    public boolean method1(Node h1,Node h2){

        return false;
    }
    public static void main(String[] args){

    }
}
