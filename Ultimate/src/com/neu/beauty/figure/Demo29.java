package com.neu.beauty.figure;

public class Demo29{

		public int fibonacci(int n){
				if(n<=0){
					return 0;
				}else if(n==1){
					return 1;
				}else{
					return fibonacci(n-1)+fibonacci(n-2);
				}
		}
		public static void main(String[] args){
				Demo29 demo = new Demo29();
				System.out.println(demo.fibonacci(3));
        }
}
