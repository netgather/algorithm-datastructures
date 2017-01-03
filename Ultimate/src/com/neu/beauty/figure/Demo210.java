package com.neu.beauty.figure;

import java.util.*;

class Stack_{

		Node top = null;
		Node newNode = null;
		int size = 0;
		public boolean isEmpty(){
			return size<=0;
		}
		public void push(int data){
			newNode = new Node(data);
			newNode.next = top;
			top = newNode;
			this.size++;
		}
		public int pop(){
			int data = top.data;
			top = top.next;
			this.size--;
			return data;
		}
		public int getTopData(){
			int data = top.data;
			return data;
		}
		public int capacity(){
			return this.size;
		}
}
class MaxMinStack{
   	    Stack_ stack;Stack_ minStack;Stack maxStack_;
		private Stack_ maxStack;
		{
			stack = new Stack_();
			minStack = new Stack_();
			maxStack = new Stack_();
		}
		public void push(int data){
			stack.push(data);
			if(minStack.isEmpty()&&maxStack.isEmpty()){
				minStack.push(data);
				maxStack.push(data);
			}else if(minStack.isEmpty()&&!maxStack.isEmpty()){
				minStack.push(data);
				if(data>maxStack.getTopData()){
					maxStack.push(data);
				}
			}else if(!minStack.isEmpty()&&maxStack.isEmpty()){
				maxStack.push(data);
				if(data<minStack.getTopData()){
					minStack.push(data);
				}
			}else{
				if(data>maxStack.getTopData()){
					maxStack.push(data);
				}else if(data<minStack.getTopData()){
					minStack.push(data);
				}
			}
        }
		public int pop(){
			int data = stack.pop();
			if(data == minStack.getTopData()){
				minStack.pop();
			}else if(data == maxStack.getTopData()){
				maxStack.pop();
			}
			return data;
		}
		public int minStackPop(){
			return minStack.pop();
		}
		public int maxStackPop(){
			return maxStack.pop();
		}
		public int getMaxStackSize(){
			return maxStack.capacity();
		}
		public int getMinStackSize(){
			return minStack.capacity();
		}
}
public class Demo210{

		public static void main(String[] args){
			MaxMinStack mms = new MaxMinStack();
			int[] array = {3,1,5,9,7,2,4,8,6,10,0};
			for(int i=0;i<array.length;i++){
				mms.push(i);
			}
			System.out.println("max:"+mms.maxStackPop());
			System.out.println("min:"+mms.minStackPop());
			System.out.println("stack size:"+mms.stack.capacity());
        }
}
