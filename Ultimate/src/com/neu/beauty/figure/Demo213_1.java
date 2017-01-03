package com.neu.beauty.figure;

/*
 *给定一个长度为N的整数数组，只允许用乘法，不能用除法(防止溢出)
 *计算任意N-1个数的组合中乘积最大的一组，并写出算法的时间复杂度
 *解法一：我们把所有可能的N-1个数的组合找出来，分别计算它们的乘积
 *        并比较大小。由于总共有N个(N-1)个数的组合，总的时间复杂度为O(N^2)
 *解法二：
 * */
class Node{
		int data;
		Node next;
		public Node(int data){
			this.data = data;
			next = null;
		}
}
class Stack{
		private Node top = null;
		private int size = 0;
		private int count = 0;
		public void push(int value){
				if(top==null){
					Node newNode = new Node(value);
				    newNode.next = top;
				    top = newNode;
				    size++;
				}else{
					int data = top.data;
					if(value>data){
							Node newNode = new Node(value);
				            newNode.next = top;
				            top = newNode;
				            size++;
					}else{
							count++;
					}
				}
		}
		public int pop(){
				int data = top.data;
				return data;
		}
		public int getCount(){
				return this.count;
		}
}
public class Demo213_1{
		private Stack stack;
		{
				stack = new Stack();
		}
		public int getMaxValue(int[] array){
				int max = 1;
				for(int i=0;i<array.length;i++){
						for(int j=0;j<array.length;j++){
										if(j==i){
											continue;
										}
								max *= array[j];
						}
						stack.push(max);
						max = 1;
				}
				return stack.pop();
		}	
		public static void main(String[] args){
				Demo213_1 demo = new Demo213_1();
				int[] array = {1,2,3,4,5};
				System.out.println(demo.getMaxValue(array));
		}
}
