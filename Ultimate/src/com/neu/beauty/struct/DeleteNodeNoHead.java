package com.neu.beauty.struct;

/**问题：①如何从无头单链表中删除节点
 *       假设有一个没有头指针的单链表，一个指针指向此单链表中间的一个节点（不是第一个，也不是最后一个）
 *       请将该节点从单链表中删除
 *      ②编写一个函数，只给定一个链表的头指针，要求只遍历一次，将单链表中的元素顺序反过来。
 * Created by lihongyan on 2015/11/4.
 */
class Node{
    public int data;
    public Node Next;
    public Node(int data){
        this.data = data;
        this.Next = null;
    }
}
public class DeleteNodeNoHead {

    private Node head = null;
    private Node tail = null;
    public void traverse(){
        Node temp = getHead();
        while(temp!=null){
            System.out.println(temp.data);
            temp = temp.Next;
        }
    }
    public void build(int data){
        if(head==null){
            Node newNode = new Node(data);
            head = newNode;
            tail = head;
        }else{
            Node newNode = new Node(data);
            tail.Next = newNode;
            tail = newNode;
        }
    }
    public Node getRandomNode(int i){
        int j = 0;
        Node result = null;
        Node temp = getHead();
        if(temp==null){
            return null;
        }
        while(temp!=null){
            if(j==i){
                result = temp;
                break;
            }else{
                temp = temp.Next;
                j++;
            }
        }
        return result;
    }
    public Node getHead() {
        return head;
    }
    public Node getTail() {
        return tail;
    }

    //分析与解法：
    //   假设给定的指针为pCurrent，Node* pNext = pCurrent -> Next
    //   (pNext指向pCurrent所指节点的下一个节点)。
    //   根据题意，pCurrent指向链表的某一个节点（除了最后一个节点），
    //   即pCurrent指向中间节点，
    //   那么此时pCurrent->Next!=null.
    //   若pCurrent指向链表中间节点的某个节点B，则需要删除B，使得A和C相连。
    //   删掉B容易，但是单链表节点并没有头指针，
    //   因此无法追溯到A，也就无法将A和C相连
    //重新考察所拥有的条件--尾指针，我们由pCurrent指向B，pNext指向C，
    //同理pNext->Next指向D，
    //不过不能简单地删除B，因为那样会使得链表被分割。
    //但是我们可以删除C，并通过pCurrent->Next=pCurrent->Next->Next
    //重新使链表连接，其中唯一丢失的是C中的data项。我们就可以用C中的数据取代B中的数据项，
    // 让B变成C，
    //然后将真正指向C的指针删除，这样我们就达到了目的。
    public void deleteNodeNoHead(Node pCurrent){
        Node pNext = pCurrent.Next;
        if(pNext==null){
            return;
        }
        pCurrent.data = pNext.data;
        pCurrent.Next = pCurrent.Next.Next;
        pNext = null;
    }
    //问题②，只给定链表的头指针，遍历一次，实现链表的反转
    public void reverse(){
        Node head_temp = getHead();
        Node current = head_temp;
        Node front = null;
        Node back = current.Next;//存储当前节点后面的节点
        if(head_temp==null){
            return;
        }
        while(current!=null){
            if(front==null){
                front = current;
                current = back;
            }else{
                front.Next = current.Next;
                current.Next = front;

            }
        }
    }
    public static void main(String[] args){
        //问题①
        DeleteNodeNoHead dnnh = new DeleteNodeNoHead();
        for(int i=0;i<=10;i++){
            dnnh.build(i);
        }
        dnnh.traverse();
        Node pCurrent = dnnh.getRandomNode(2);
        dnnh.deleteNodeNoHead(pCurrent);
        dnnh.traverse();

        //问题②
        dnnh.reverse();
        dnnh.traverse();
    }
}
