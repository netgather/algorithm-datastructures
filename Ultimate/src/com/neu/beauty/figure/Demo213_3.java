package com.neu.beauty.figure;

import java.util.Arrays;
class MinStack{
				private Node top = null;
				private int size = 0;
				public void push(int value){
								if(top == null){
											Node newNode = new Node(value);
											newNode.next = top;
											top = newNode;
											size++;
								}else{
											int data = top.data;
											if(value<data){
														Node newNode = new Node(value);
														newNode.next = top;
														top = newNode;
														size++;
											}else{
														return;
											}
								}
				}
				public int pop(){
								int data = top.data;
								top = top.next;
								size--;
								return data;
				}
}
class MaxStack{
				private Node top = null;
				private int size = 0;
				public void push(int value){
								if(top == null){
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
														return;
											}
								}
				}
				public int pop(){
								int data = top.data;
								top = top.next;
								size--;
								return data;
				}
}
public class Demo213_3{

				public int getMaxNegativeNum(int[] array){
								MaxStack ms = new MaxStack();
								for(int i=0;i<array.length;i++){
												if(array[i]<0){
															ms.push(array[i]);
												}else{
															continue;
												}
								}
								return ms.pop();
				}
				public int getMinNegativeNum(int[] array){
								MinStack min = new MinStack();
				      	for(int i=0;i<array.length;i++){
												if(array[i]<0){
															min.push(array[i]);
												}
								}
								return min.pop();
				}
				public int getMaxPositiveNum(int[] array){

								for(int i=0;i<array.length;i++){
												if(array[i]<0){
															
												}
							  }
								return 0;
				}
				public int getMinPositiveNum(int[] array){
								MinStack min = new MinStack();
								for(int i=0;i<array.length;i++){
												if(array[i]>0){
															min.push(array[i]);
												}else{
															continue;
												}
								}
								return min.pop();
				}
				public int getMulti(int[] array){
								int value = 1;
								for(int i=0;i<array.length;i++){
											value *= array[i];
								}
								return value;
				}
				public boolean isHavingPositive(int[] array){
								for(int i=0;i<array.length;i++){
												if(array[i]>0){
															return true;
												}
								}
								return false;
				}
				public int getMaxValue(int[] array){
								Arrays.sort(array);
								int k = 0;
								int index = 0;
								int P_value = getMulti(array);
								int[] temp = new int[array.length-1];
								if(P_value==0){
												for(int i=0;i<array.length;i++){
															if(array[i]==0){
																	 index = i;
																	 break;
															}else{
																	 continue;
															}
												}
												for(int i=0;i<array.length;i++){
															if(i!=index){
																	 temp[k++] =  array[i];
															}
												}
												int Q_value = getMulti(temp);
												if(Q_value==0){
															return 0;
												}else if(Q_value>0){
															return Q_value;
												}else{
															return 0;
												}
								}else if(P_value<0){
												int num = getMaxNegativeNum(array);
												return P_value/num;
								}else{
												if(isHavingPositive(array)){
															 int num = getMinPositiveNum(array);
															 return P_value/num;
												}else{
															 int num = getMinNegativeNum(array);
															 return P_value/(-num);
												}
								}
								

				}
				public static void main(String[] args){
								
								Demo213_3 demo = new Demo213_3();
								int[] array = {-3,-2,-2,-3,-4,-5,-1,-4,-6,-7,2};
								System.out.println(demo.getMaxValue(array));
				}
}
